package actions;

import java.io.IOException;
import java.util.List; //追記

import javax.servlet.ServletException;

import actions.views.EmployeeView; //追記
import actions.views.OpinionView;
import actions.views.ReportView; //追記
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;  //追記
import services.OpinionService;
import services.ReportService;  //追記

/**
 * トップページに関する処理を行うActionクラス
 *
 */
public class TopAction extends ActionBase {

    private ReportService r_service; //追記
    private OpinionService o_service; //オリジナル


    /**
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        r_service = new ReportService(); //追記
        o_service = new OpinionService(); //オリジナル


        //メソッドを実行
        invoke();

        r_service.close(); //追記
        o_service.close(); //オリジナル

    }

    /**
     * 一覧画面を表示する
     */
    public void index() throws ServletException, IOException {

        // 以下追記

        //セッションからログイン中の従業員情報を取得
        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);


        //ログイン中の従業員が作成した日報データを、指定されたページ数の一覧画面に表示する分取得する
        int r_page = getPage();
        List<ReportView> reports = r_service.getMinePerPage(loginEmployee, r_page);

        //オリジナル  ログイン中の従業員が作成したご意見・ご要望データを、指定されたページ数の一覧画面に表示する分取得する
        int o_page = getPage();
        List<OpinionView> opinions = o_service.getMinePerPage(loginEmployee, o_page);


        //ログイン中の従業員が作成した日報データの件数を取得
        long myReportsCount = r_service.countAllMine(loginEmployee);
        //オリジナル  ログイン中の従業員が作成したご意見・ご要望データの件数を取得
        long myOpinionsCount = o_service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.REPORTS, reports); //取得した日報データ
        putRequestScope(AttributeConst.REP_COUNT, myReportsCount); //ログイン中の従業員が作成した日報の数
        putRequestScope(AttributeConst.PAGE, r_page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //オリジナル  ログイン中の従業員が作成したご意見・ご要望データの件数を取得
        putRequestScope(AttributeConst.OPINIONS, opinions); //取得したご意見・ご要望データ
        putRequestScope(AttributeConst.OPI_COUNT, myOpinionsCount); //ログイン中の従業員が作成したご意見・ご要望の数
        putRequestScope(AttributeConst.PAGE, o_page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数


        //↑ここまで追記

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }

}