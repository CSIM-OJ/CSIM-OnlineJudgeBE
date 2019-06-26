package csim.scu.onlinejudge.dao.domain.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentResult {

    private String account;
    private double rate;
    private String comment;

}
