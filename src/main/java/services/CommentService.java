package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CommentConverter;
import actions.views.CommentView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.OpinionConverter;
import actions.views.OpinionView;
import constants.JpaConst;
import models.validators.CommentValidator;


/*
 * テーブルの操作関連
 */

public class CommentService extends ServiceBase {

    /*
     * 指定した従業員が作成したコメントと、コメントの対象となる報告を、指定されたページ数の一覧画面に表示する分取得しCommentViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<CommentView> getMinePerPage(EmployeeView employee, int page){

        List<models.Comment> comments = em.createNamedQuery(JpaConst.Q_COM_GET_ALL_MINE,  models.Comment.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return CommentConverter.toViewList(comments);

    }

    /*
     * 指定した従業員が作成したコメントデータの件数を取得し、返却する
     * @param employee
     * @return コメントデータの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_COM_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }


    /*
     * 指定されたページ数の一覧画面に表示するコメントデータを取得し、CommentViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<CommentView> getAllPerPage(int page){


        List<models.Comment> comments = em.createNamedQuery(JpaConst.Q_COM_GET_ALL, models.Comment.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return CommentConverter.toViewList(comments);
    }


    /**-------------------------------------------------------------------------------------------------------仮で作成 使わなければ削除
     * 全てのコメントをidの降順に取得する
     * @return
     */
    public List<CommentView> getAll() {
        List<models.Comment> comments_list = em.createNamedQuery(JpaConst.Q_COM_GET_ALL, models.Comment.class)
                .getResultList();
        return CommentConverter.toViewList(comments_list);
    }


    /*
     * コメントテーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long comments_count = (long) em.createNamedQuery(JpaConst.Q_COM_COUNT, Long.class)
                .getSingleResult();
        return comments_count;
    }


    /*
     * idを条件に取得したデータをCommentViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public CommentView findOne(int id) {
        return CommentConverter.toView(findOneInternal(id));
    }

    /**
     *idを条件に取得したデータをOpinionViewのインスタンスで返却する
     *@param id
     *@return 取得データのインスタンス
     */
    public OpinionView findOneOpinion(int id) {
        return OpinionConverter.toView(findOneOpinionInternal(id));
    }

    /*
     * 画面から入力されたコメントの登録内容を元にデータを1件作成し、コメントテーブルに登録する
     * @param cv コメントの登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(CommentView cv){

        //登録日時、更新日時を現在時刻に設定


        List<String> errors = CommentValidator.validate(cv);
        if(errors.size() == 0) {

            LocalDateTime now = LocalDateTime.now();
            cv.setCreatedAt(now);
            cv.setUpdatedAt(now);
            createInternal(cv);

        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /*
     * 画面から入力されたコメントの登録内容を元に、コメントデータを更新する
     * @param cv コメントの更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(CommentView cv){

        //バリデーションを行う
        List<String> errors = CommentValidator.validate(cv);

        if(errors.size() == 0) {

            updateInternal(cv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * 物理削除を実行する
     * @param cv
     */
    public void destroy(int cv) {


        em.getTransaction().begin();
        em.remove(findOneInternal(cv));
        em.getTransaction().commit();

    }


    /*
     * idを条件にデータを1件取得する（コメント情報）
     * @param id
     * @return 取得データのインスタンス
     */
    private  models.Comment findOneInternal(int id) {
        return em.find(models.Comment.class, id);
    }

    /**
     * idを条件にデータを1件取得する（ご意見・ご要望情報）
     * @param id
     * @return 取得データのインスタンス
     */
    private models.Opinion findOneOpinionInternal(int id){
        return em.find(models.Opinion.class, id);
    }



    /*
     * コメントデータを1件登録する
     * @param cv コメントデータ
     */
    private void createInternal(CommentView cv) {

        em.getTransaction().begin();
        em.persist(CommentConverter.toModel(cv));
        em.getTransaction().commit();


    }

    /*
     * コメントデータを更新する
     * @param cv コメントデータ
     */
    private void updateInternal(CommentView cv) {


        em.getTransaction().begin();
        models.Comment c = findOneInternal(cv.getId());
        CommentConverter.copyViewToModel(c, cv);
        em.getTransaction().commit();


    }


}
