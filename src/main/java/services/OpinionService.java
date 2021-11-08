package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.OpinionConverter;
import actions.views.OpinionView;
import constants.JpaConst;
import models.Opinion;
import models.validators.OpinionValidator;

/*
 * ご意見・ご要望テーブルの操作に関わる処理を行うクラス
 */
public class OpinionService extends ServiceBase {

    /*
     * 指定した従業員が作成したご意見・ご要望データを、指定されたページ数の一覧画面に表示する分取得しOpinionViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<OpinionView> getMinePerPage(EmployeeView employee, int page){

        List<Opinion> opinions = em.createNamedQuery(JpaConst.Q_OPI_GET_ALL_MINE, Opinion.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return OpinionConverter.toViewList(opinions);
    }

    /*
     * 指定した従業員が作成したご意見・ご要望データの件数を取得し、返却する
     * @param employee
     * @return ご意見・ご要望データの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_OPI_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();


        return count;
    }

    /*
     * 指定されたページ数の一覧画面に表示するご意見・ご要望データを取得し、OpinionViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<OpinionView> getAllPerPage(int page){

        List<Opinion> opinions = em.createNamedQuery(JpaConst.Q_OPI_GET_ALL, Opinion.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return OpinionConverter.toViewList(opinions);
    }

    /*
     * ご意見・ご要望テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long opinions_count = (long) em.createNamedQuery(JpaConst.Q_OPI_COUNT, Long.class)
                .getSingleResult();
        return opinions_count;
    }

    /*
     * idを条件に取得したデータをOpinionViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public OpinionView findOne(int id) {
        return OpinionConverter.toView(findOneInternal(id));

    }

    /*
     * 画面から入力されたご意見・ご要望の登録内容を元にデータを1件作成し、ご意見・ご要望テーブルに登録する
     * @param ov ご意見・ご要望の登録内容
     * return バリデーションで発生したエラーのリスト
     */
    public List<String> create(OpinionView ov){
        List<String> errors = OpinionValidator.validate(ov);
        if(errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            ov.setCreatedAt(ldt);
            ov.setUpdatedAt(ldt);
            createInternal(ov);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /*
     *画面から入力されたご意見・ご要望の登録内容を元に、ご意見・ご要望データを登録する
     *@param ov ご意見・ご要望の更新内容
     *@return バリデーションで発生したエラーのリスト
     */
    public List<String> update(OpinionView ov){

        //バリデーションを行う
        List<String> errors = OpinionValidator.validate(ov);

        if(errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            ov.setUpdatedAt(ldt);


            updateInternal(ov);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /*
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Opinion findOneInternal(int id) {
        return em.find(Opinion.class, id);
    }

    /*
     * ご意見・ご要望データを1件登録する
     * @param ov ご意見・ご要望データ
     */
    private void createInternal(OpinionView ov) {


        em.getTransaction().begin();
        em.persist(OpinionConverter.toModel(ov));
        em.getTransaction().commit();


    }

    /*
     * ご意見・ご要望データを更新する
     * @param ov ご意見・ご要望データ
     */
    private void updateInternal(OpinionView ov) {


        em.getTransaction().begin();
        Opinion o = findOneInternal(ov.getId());
        OpinionConverter.copyViewToModel(o, ov);
        em.getTransaction().commit();
    }
}
