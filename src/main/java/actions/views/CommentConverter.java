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
                cv.getContent());

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
                c.getContent());
    }

    /*
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param List DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<CommentView> toViewList(List<Comment> list){
        List<CommentView> cvs = new ArrayList<>();

        for(Comment c :list) {
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
        c.setContent(cv.getContent());
    }

    /*
     * DTOモデルの全フィールドの内容をViewモデルのフィールドにコピーする
     * @param c DTOモデル
     * @param cv Viewモデル
     */
    public static void copyModelToView(Comment c, CommentView cv) {
        cv.setId(c.getId());
        cv.setEmployee(EmployeeConverter.toView(c.getEmployee()));
        cv.setContent(c.getContent());
    }

}
