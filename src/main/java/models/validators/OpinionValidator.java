package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.OpinionView;
import constants.MessageConst;

/*
 * ご意見・ご要望インスタンスに設定されている値のバリデーションを行う
 */

public class OpinionValidator {


    /*
     * ご意見・ご要望インスタンスの各項目についてバリデーションを行う
     * @param ov ご意見・ご要望インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(OpinionView ov){
        List<String> errors = new ArrayList<String>();

        //概要のチェック 概要が空文字(入力値がある状態)でなければ、List errorsにOverViewErrorという要素を追加する
        String overViewError = validateOverView(ov.getOverView());
        if(!overViewError.equals("")) {
            errors.add(overViewError);
        }

        //内容のチェック
        String contentError = validateContent(ov.getContent());
        if(!contentError.equals("")) {
            errors.add(contentError);//add(インデックス,要素)でリストに追加する。インデックスの指定がない場合はリストの最後に追加。
        }

        return errors;

    }

        /*
         * 概要に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
         * @param overView 概要
         * @return エラーメッセージ
         */
        private static String validateOverView(String overView) {
            if(overView == null || overView.equals("")) {
                return MessageConst.E_NOOVERVIEW.getMessage();
            }

            //入力値がある場合は空文字を返却
            return "";
        }

        /*
         * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
         * @param content 内容
         * @return エラーメッセージ
         */
       private static String validateContent(String content) {
           if(content == null || content.equals("")) {
               return MessageConst.E_NOCONTENT.getMessage();
           }

           //入力値がある場合は空文字を返却
           return "";
       }

}
