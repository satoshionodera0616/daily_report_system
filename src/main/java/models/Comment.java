package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * コメントデータのDTOモデル
 */
@Table(name = JpaConst.TABLE_COM)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    /*
     * id
     */

    @Id
    @Column(name = JpaConst.COM_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     * コメントをした従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.COM_COL_EMP, nullable = false)
    private Employee employee;

    /*
     * コメントの内容
     */
    @Lob
    @Column(name = JpaConst.COM_COL_CONTENT, nullable = false)
    private String content;

}
