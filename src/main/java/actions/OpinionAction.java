package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.OpinionView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.OpinionService;

/*
 * ご意見・ご要望に関する処理を行うActionクラス
 */

public class OpinionAction extends ActionBase{

    private OpinionService service;

    /*
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException{


        service = new OpinionService();

        //メソッドを実行
        invoke();
        service.close();
    }


    /*
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException,IOException{


        //指定されたページ数の一覧画面に表示するご意見・ご要望データを取得
        int page = getPage();
        List<OpinionView> opinions= service.getAllPerPage(page);


        //全ご意見・ご要望データの件数を取得
        long opinionsCount = service.countAll();


        putRequestScope(AttributeConst.OPINION, opinions); //取得したご意見・ご要望データ
        putRequestScope(AttributeConst.OPI_COUNT, opinionsCount); //全てのご意見・ご要望データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数


        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }


        //一覧画面を表示する
        forward(ForwardConst.FW_OPI_INDEX);

    }

}
