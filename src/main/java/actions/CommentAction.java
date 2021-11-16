package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CommentView;
import actions.views.OpinionView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
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


        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
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
        OpinionView ov = service.findOne2(toNumber(getRequestParam(AttributeConst.OPI_ID)));

        putRequestScope(AttributeConst.OPINION, ov);

        //記入画面を表示する
        forward(ForwardConst.FW_COM_NEW);

    }


    /*
     * コメントの新規登録を行う(create)
     * @throws ServletException
     * @throws IOException
     */
   /* public void create() throws ServletException, IOException {


        //CSRF対策 tokenのチェック
       if(checkToken()) {

       //セッションからログイン中の従業員情報を取得
           EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

       //パラメータの値をもとにコメント情報のインスタンスを作成する
       CommentView cv = new CommentView(
               null,
               ev,
               null,→ご意見情報取得して変数定義してインスタンス作成しないといけないけどどうやって情報取得する？？
               getRequestParam(AttributeConst.COM_CONTENT),
               null,
               null);
       }
    }*/


    /*
     * コメント詳細画面（ご意見・ご要望詳細画面）を表示する
     */


    /*
     * コメント編集ページを表示する（edit）
     */


    /*
     * コメントの登録内容を更新する（update）
     */


    /*
     * コメントを削除する（destroy）
     */

}
