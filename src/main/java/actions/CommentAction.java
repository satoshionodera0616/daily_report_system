package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CommentView;
import actions.views.EmployeeView;
import actions.views.OpinionView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.CommentService;

/*
 * コメントに関する処理を行うActionクラス
 */

public class CommentAction extends ActionBase {

    private CommentService service;

    /*
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {


        service = new CommentService();


        //メソッドを実行
        invoke();

        service.close();
    }


    /*
     * コメント一覧画面を表示する（index）
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {


        //指定されたページ数のコメント一覧画面に表示する報告とコメントのデータを取得
        int page = getPage();
        List<CommentView> comments = service.getAllPerPage(page);

        //全コメントのデータの件数を取得する
        long commentsCount = service.countAll();

        putRequestScope(AttributeConst.COMMENT, comments); //取得したコメントデータ
        putRequestScope(AttributeConst.COM_COUNT, commentsCount); //全てのコメントデータの件数
        putRequestScope(AttributeConst. PAGE, page); //ページ数
        putRequestScope(AttributeConst. MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替えて、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_COM_INDEX);
    }


    /*
     * コメント記入画面を表示する(new)
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException{

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策

        //idを条件に、記入ページに表示するご意見・ご要望の情報を取得
        OpinionView ov = service.findOneOpinion(toNumber(getRequestParam(AttributeConst.OPI_ID)));

        putRequestScope(AttributeConst.OPINION, ov);

        //記入画面を表示する
        forward(ForwardConst.FW_COM_NEW);

    }


    /*
     * コメントの新規登録を行う(create)
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {


        //CSRF対策 tokenのチェック
       if(checkToken()) {

       //セッションからログイン中の従業員情報を取得
           EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

       //idを条件にご意見・ご要望情報を取得
           OpinionView ov = service.findOneOpinion(toNumber(getRequestParam(AttributeConst.OPI_ID)));
       //パラメータの値をもとにコメント情報のインスタンスを作成する
       CommentView cv = new CommentView(
               null,
               ev,
               ov,
               getRequestParam(AttributeConst.COM_CONTENT),
               null,
               null);


       //コメント情報登録
       List<String> errors = service.create(cv);

       if(errors.size() > 0) {

           //登録中にエラーがあった場合
           putRequestScope(AttributeConst.TOKEN, getTokenId());
           putRequestScope(AttributeConst.COMMENT, cv);
           putRequestScope(AttributeConst.ERR, errors);

           //コメント記入画面を再表示する
           forward(ForwardConst.FW_COM_NEW);

       }else {
           //登録中にエラーが無かった場合

           //セッションに登録完了のフラッシュメッセージを設定
           putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

           //一覧画面にリダイレクト
           redirect(ForwardConst.ACT_COM, ForwardConst.CMD_INDEX);
       }
     }
  }


    /*
     * コメント編集画面を表示する（edit）
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException{


       OpinionView ov = service.findOneOpinion(toNumber(getRequestParam(AttributeConst.OPI_COMMENTEDIT_ID)));


       CommentView cv = service.findOne(toNumber(getRequestParam(AttributeConst.COM_ID)));


       EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);


       if(cv == null || ev.getId() != cv.getEmployee().getId()) {

           // 該当のコメントデータが存在しない、または
           // ログインしている従業員がコメントの作成者でない場合はエラー画面を表示
           forward(ForwardConst.FW_ERR_UNKNOWN);

       }else {


           putRequestScope(AttributeConst.TOKEN, getTokenId());
           putRequestScope(AttributeConst.COMMENT, cv);
           putRequestScope(AttributeConst.OPINION, ov);

           //編集画面を表示
           forward(ForwardConst.FW_COM_EDIT);
       }
    }


    /*
     * コメントの登録内容を更新する（update）
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException{

        //CSRF対策
        if(checkToken()) {


            CommentView cv = service.findOne(toNumber(getRequestParam(AttributeConst.COM_ID)));


            cv.setContent(getRequestParam(AttributeConst.COM_CONTENT));


            //コメントデータを更新する
            List<String> errors = service.update(cv);


            if(errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); // CSRF対策用トークン
                putRequestScope(AttributeConst.COMMENT, cv); // 入力されたコメント情報
                putRequestScope(AttributeConst.ERR, errors); // エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_COM_EDIT);

            }else {

                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH,MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_OPI, ForwardConst.CMD_INDEX);
            }
        }

    }



    /*
     * コメントを削除する（destroy）
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException{


        // CSRF対策 tokenのチェック
        if( checkToken()) {


            // idを条件にコメントデータを1件取得する
            int cv = toNumber(getRequestParam(AttributeConst.COM_ID));

            // 取得したデータを物理削除する
            service.destroy(cv);

            // セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());


            // 一覧画面にリダイレクト
            redirect(ForwardConst.ACT_OPI,ForwardConst.CMD_INDEX);
        }

    }

}
