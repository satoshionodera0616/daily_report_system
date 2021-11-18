package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Comment;

public class CommentConverter {

    /*
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param cv CommentViewのインスタンス
     * @return Commentのインスタンス
     */
    public static Comment toModel(CommentView cv) {
        return new Comment(
                cv.getId(),
                EmployeeConverter.toModel(cv.getEmployee()),
                OpinionConverter.toModel(cv.getOpinion()),
                cv.getContent(),
                cv.getCreatedAt(),
                cv.getUpdatedAt());

    }

    /*
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param c Commentのインスタンス
     * @return CommentViewのインスタンス
     */
    public static CommentView toView(Comment c) {
        if(c == null) {
            return null;
        }

        return new CommentView(
                c.getId(),
                EmployeeConverter.toView(c.getEmployee()),
                OpinionConverter.toView(c.getOpinion()),
                c.getContent(),
                null,
                null);

    }

    /*
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param List DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<CommentView> toViewList(List<Comment> list){
        List<CommentView> cvs = new ArrayList<>();

        for(Comment c : list) {
            cvs.add(toView(c));
        }

        return cvs;
    }

    /*
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param c DTOモデル
     * @param cv Viewモデル
     */
    public static void copyViewToModel(Comment c, CommentView cv) {
        c.setId(cv.getId());
        c.setEmployee(EmployeeConverter.toModel(cv.getEmployee()));
        c.setOpinion(OpinionConverter.toModel(cv.getOpinion()));
        c.setContent(cv.getContent());
        c.setCreatedAt(cv.getCreatedAt());
        c.setUpdatedAt(cv.getUpdatedAt());
    }

    /*
     * DTOモデルの全フィールドの内容をViewモデルのフィールドにコピーする
     * @param c DTOモデル
     * @param cv Viewモデル
     */
    public static void copyModelToView(Comment c, CommentView cv) {
        cv.setId(c.getId());
        cv.setEmployee(EmployeeConverter.toView(c.getEmployee()));
        cv.setOpinion(OpinionConverter.toView(c.getOpinion()));
        cv.setContent(c.getContent());
        cv.setCreatedAt(c.getCreatedAt());
        cv.setUpdatedAt(c.getUpdatedAt());
    }

}
