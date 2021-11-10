package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * ご意見・ご要望の情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしのコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)

public class OpinionView {


    /*
     * id
     */
    private Integer id;


    /*
     * ご意見・ご要望を報告した従業員
     */
    private EmployeeView employee;


    /*
     * ご意見を頂戴した日付
     */
    private LocalDate opinionDate;


    /*
     * 概要
     */
    private String overView;


    /*
     * ご意見・ご要望の内容
     */
    private String content;


    /*
     * 登録日時
     */
    private LocalDateTime createdAt;


    /*
     * 更新日時
     */
    private LocalDateTime updatedAt;


    /*
     * 削除された報告かどうか（現存：0、削除済み：1）
     */
    private Integer deleteFlag;


}
