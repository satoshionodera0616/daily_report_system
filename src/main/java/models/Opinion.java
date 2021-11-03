package models;

import constants.JpaConst;

/*
 * ご意見・ご要望データのDTOモデル
 */
@Table(name = JpaConst.TABLE_OPI)
@NamedQueries({
        NamedQuery(
                name = JpaConst.Q_OPI_GET_ALL,
                query = JpaConst.Q_OPI_GET_ALL_DEF),
        NamedQuery(

                )

})

public class Opinion {

}
