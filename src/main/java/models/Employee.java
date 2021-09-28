package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 従業員データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_EMP)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_EMP_GET_ALL,
            query = JpaConst.Q_EMP_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_EMP_COUNT,
            query = JpaConst.Q_EMP_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_EMP_COUNT_RESISTERED_BY_CODE,  // 指定された社員番号がすでにデータベースにそんざいしているかを調べるもの
            query = JpaConst.Q_EMP_COUNT_RESISTERED_BY_CODE_DEF),
    @NamedQuery(
            name = JpaConst.Q_EMP_GET_BY_CODE_AND_PASS, // 従業員がログインするときに社員番号とパスワードが正しいかをチェックするためのもの
            query = JpaConst.Q_EMP_GET_BY_CODE_AND_PASS_DEF)
})


@Getter // 全てのクラスフィールドについてgetterを自動生成する（Lombok）
@Setter // 全てのクラスフィールドについてsetterを自動生成する（Lombok）
@NoArgsConstructor // 引数なしコンストラクタを自動生成する（Lombok）
@AllArgsConstructor // 全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する
@Entity
public class Employee{

    /*
     * id
     */
    @Id
    @Column(name = JpaConst.EMP_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /*
     * 社員番号
     */
    @Column(name = JpaConst.EMP_COL_CODE,nullable = false,unique = true) //unique=true ： 一意制約。すでに存在している社員番号は登録できない旨をデータベースに指定するため設定
    private String code;


    /*
     * 氏名
     */
    @Column(name = JpaConst.EMP_COL_NAME,nullable = false)
    private String name;


    /*
     * パスワード
     */
    @Column(name = JpaConst.EMP_COL_PASS,length = 64,nullable = false) //length=64 ： SHA256(ハッシュ関数)を利用して文字列をデータベースに保存するときに、SHA256は必ず64文字で変換するから。
    private String password;


    /*
     * 管理者権限があるかどうか（一般：0、管理者：1）
     */
    @Column(name = JpaConst.EMP_COL_ADMIN_FLAG,nullable = false)
    private Integer adminFlag;


    /*
     * 登録日時
     */
    @Column(name = JpaConst.EMP_COL_CREATED_AT,nullable = false)
    private LocalDateTime createdAt;


    /*
     * 更新日時
     */
    @Column(name = JpaConst.EMP_COL_UPDATED_AT,nullable = false)
    private LocalDateTime updatedAt;


    /*
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.EMP_COL_DELETE_FLAG,nullable = false)
    private Integer deleteFlag;
}
