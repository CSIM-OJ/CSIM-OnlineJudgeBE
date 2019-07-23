package csim.scu.onlinejudge.dao.domain.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentResult implements Serializable {

    private String account;
    private int score;
    private int correctValue;
    private int readValue;
    private int skillValue;
    private int completeValue;
    private int wholeValue;

}
