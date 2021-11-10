package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * ご意見・ご要望データのDTOモデル
 */
@Table(name = JpaConst.TABLE_OPI)
@NamedQueries({
        @NamedQuery(
                name = JpaConst.Q_OPI_GET_ALL,
                query = JpaConst.Q_OPI_GET_ALL_DEF),
        @NamedQuery(
                name = JpaConst.Q_OPI_COUNT,
                query = JpaConst.Q_OPI_COUNT_DEF),
        @NamedQuery(
                name = JpaConst.Q_OPI_GET_ALL_MINE,
                query = JpaConst.Q_OPI_GET_ALL_MINE_DEF),
        @NamedQuery(
                name = JpaConst.Q_OPI_COUNT_ALL_MINE,
                query = JpaConst.Q_OPI_COUNT_ALL_MINE_DEF)
})


@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する
@Entity

public class Opinion {


    /*
     * id
     */
    @Id
    @Column(name = JpaConst.OPI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //フィールド


    /*
     * ご意見・ご要望を報告した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.OPI_COL_EMP, nullable = false)
    private Employee employee;


    /*
     * ご意見・ご要望を頂戴した日付
     */
    @Column(name = JpaConst.OPI_COL_OPI_DATE, nullable = false)
    private LocalDate opinionDate; //localDateだと時分秒が入らない。


    /*
     * ご意見・ご要望の概要
     */
    @Column(name = JpaConst.OPI_COL_OVERVIEW, length = 255, nullable = false)
    private String overView;


    /*
     * ご意見・ご要望の内容
     */
    @Lob //テキストエリアの指定を行うアノテーション。これを指定することで、改行もデータベースに保存される。
    @Column(name = JpaConst.OPI_COL_CONTENT, nullable = false)
    private String content;


    /*
     * 登録日時
     */
    @Column(name = JpaConst.OPI_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /*
     * 更新日時
     */
    @Column(name = JpaConst.OPI_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /*
     * 削除された報告かどうか（現存：0、削除済み：1）
     */
    @Column(name = JpaConst.OPI_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

}
