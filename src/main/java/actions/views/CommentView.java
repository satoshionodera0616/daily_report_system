package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {


    private Integer id;  //id

    private EmployeeView employee;  //コメントを作成した従業員

    private OpinionView opinion;  //コメントをつけたご意見・ご要望の報告

    private String content;  //コメントの内容

    private LocalDateTime createdAt;  //登録日時

    private LocalDateTime updatedAt;  //更新日時

}
