package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.OpinionView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
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
        List<OpinionView> opinions = service.getAllPerPage(page);


        //全ご意見・ご要望データの件数を取得
        long opinionsCount = service.countAll();


        putRequestScope(AttributeConst.OPINIONS, opinions); //取得したご意見・ご要望データ
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


    /*
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException{


        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン


        //あらかじめご意見・ご要望情報の空インスタンスに、ご意見・ご要望を報告した日付＝今日の日付を設定しておく

        OpinionView ov = new OpinionView();
        ov.setOpinionDate(LocalDate.now());
        putRequestScope(AttributeConst.OPINION, ov); //日付のみ設定済みのご意見・ご要望インスタンス


        // 新規登録画面を表示
        forward(ForwardConst.FW_OPI_NEW);


    }


    /*
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException{


        //CSRF対策 tokenのチェック
        if(checkToken()) {


            //ご意見・ご要望を頂戴した日付が入力されていなければ、今日の日付を設定
            LocalDate day = null;
            if(getRequestParam(AttributeConst.OPI_DATE) == null || getRequestParam(AttributeConst.OPI_DATE).equals("")) {
                day = LocalDate.now();
            }else {
                day = LocalDate.parse(getRequestParam(AttributeConst.OPI_DATE));
            }


            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);


            //パラメータの値をもとにご意見・ご要望情報のインスタンスを作成する
            OpinionView ov = new OpinionView(
                    null,
                    ev, //ログインしている従業員を、ご意見・ご要望報告者として登録する
                    day,
                    getRequestParam(AttributeConst.OPI_OVERVIEW),
                    getRequestParam(AttributeConst.OPI_CONTENT),
                    null,
                    null);


            //ご意見・ご要望情報登録
            List<String> errors = service.create(ov);


            if(errors.size() > 0) {
                //登録中にエラーがあった場合


                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.OPINION, ov); //入力されたご意見・ご要望情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト


                //新規登録画面を再表示
                forward(ForwardConst.FW_OPI_NEW);

            }else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH,MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_OPI, ForwardConst.CMD_INDEX);
            }
        }
    }


    /*
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException{


        //idを条件にご意見・ご要望データを取得する
        OpinionView ov = service.findOne(toNumber(getRequestParam(AttributeConst.OPI_ID)));

        if(ov == null) {
            //該当のご意見・ご要望データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        }else {


            putRequestScope(AttributeConst.OPINION, ov);//取得したご意見・ご要望データ


            //詳細画面を表示
            forward(ForwardConst.FW_OPI_SHOW);
        }
    }

}
