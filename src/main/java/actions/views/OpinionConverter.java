package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Opinion;

/*
 * ご意見・ご要望データのDTOモデル⇔Viewモデルの変換を行うクラス
 */

public class OpinionConverter {

    /*
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param op OpinionViewのインスタンス
     * @return Opinionのインスタンス
     */
    public static Opinion toModel(OpinionView ov) {
        return new Opinion(
                ov.getId(),
                EmployeeConverter.toModel(ov.getEmployee()),
                ov.getOpinionDate(),
                ov.getOverView(),
                ov.getContent(),
                ov.getCreatedAt(),
                ov.getUpdatedAt(),
                ov.getDeleteFlag() == null
                        ? null
                        : ov.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ?JpaConst.OPI_DEL_TRUE
                                :JpaConst.OPI_DEL_FALSE);
    }

    /*
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param o Opinionのインスタンス
     * @return OpinionViewのインスタンス
     */
    public static OpinionView toView(Opinion o) {


        if (o == null) {
            return null;
        }


        return new OpinionView(
                o.getId(),
                EmployeeConverter.toView(o.getEmployee()),
                o.getOpinionDate(),
                o.getOverView(),
                o.getContent(),
                o.getCreatedAt(),
                o.getUpdatedAt(),
                o.getDeleteFlag() == null
                        ? null
                        : o.getDeleteFlag() == JpaConst.OPI_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }


    /*
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<OpinionView> toViewList(List<Opinion> list){
        List<OpinionView> evs = new ArrayList<>();


        for(Opinion o : list) {
            evs.add(toView(o));
        }

        return evs;
    }


    /*
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param o DTOモデル（コピー先）
     * @param op Viewモデル（コピー元）
     */
    public static void copyViewToModel(Opinion o, OpinionView ov) {
        o.setId(ov.getId());
        o.setEmployee(EmployeeConverter.toModel(ov.getEmployee()));
        o.setOpinionDate(ov.getOpinionDate());
        o.setOverView(ov.getOverView());
        o.setContent(ov.getContent());
        o.setCreatedAt(ov.getCreatedAt());
        o.setUpdatedAt(ov.getUpdatedAt());
        o.setDeleteFlag(ov.getDeleteFlag());
    }


    /*
     * DTOモデルの全フィールドの内容をViewモデルのフィールドにコピーする
     * @param o DTOモデル（コピー元）
     * @param op Viewモデル（コピー先）
     */
    public static void copyModelToView(Opinion o, OpinionView ov) {
        ov.setId(o.getId());
        ov.setEmployee(EmployeeConverter.toView(o.getEmployee()));
        ov.setOpinionDate(o.getOpinionDate());
        ov.setOverView(o.getOverView());
        ov.setContent(o.getContent());
        ov.setCreatedAt(o.getCreatedAt());
        ov.setUpdatedAt(o.getUpdatedAt());
        ov.setDeleteFlag(o.getDeleteFlag());

    }

}
