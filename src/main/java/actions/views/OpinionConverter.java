package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Opinion;

/*
 * 日報データのDTOモデル⇔Viewモデルの変換を行うクラス
 */

public class OpinionConverter {

    /*
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param op OpinionViewのインスタンス
     * @return Opinionのインスタンス
     */
    public static Opinion toModel(OpinionView op) {
        return new Opinion(
                op.getId(),
                EmployeeConverter.toModel(op.getEmployee()),
                op.getOpinionDate(),
                op.getOverView(),
                op.getContent(),
                op.getCreatedAt(),
                op.getUpdatedAt());
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
                o.getUpdatedAt());
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
    public static void copyViewToModel(Opinion o, OpinionView op) {
        o.setId(op.getId());
        o.setEmployee(EmployeeConverter.toModel(op.getEmployee()));
        o.setOpinionDate(op.getOpinionDate());
        o.setOverView(op.getOverView());
        o.setContent(op.getContent());
        o.setCreatedAt(op.getCreatedAt());
        o.setUpdatedAt(op.getUpdatedAt());
    }


    /*
     * DTOモデルの全フィールドの内容をViewモデルのフィールドにコピーする
     * @param o DTOモデル（コピー元）
     * @param op Viewモデル（コピー先）
     */
    public static void copyModelToView(Opinion o, OpinionView op) {
        op.setId(o.getId());
        op.setEmployee(EmployeeConverter.toView(o.getEmployee()));
        op.setOpinionDate(o.getOpinionDate());
        op.setOverView(o.getOverView());
        op.setContent(o.getContent());
        op.setCreatedAt(o.getCreatedAt());
        op.setUpdatedAt(o.getUpdatedAt());

    }

}
