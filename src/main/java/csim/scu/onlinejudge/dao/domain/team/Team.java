package csim.scu.onlinejudge.dao.domain.team;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import csim.scu.onlinejudge.dao.domain.base.BaseEntity;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.*;
import java.util.List;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)})
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "problem_id")
    private Problem problem;
    private String studentAccount;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<CommentResult> commentResults;
    private double score;

    public Team(Problem problem, String studentAccount, List<CommentResult> commentResults) {
        this.problem = problem;
        this.studentAccount = studentAccount;
        this.commentResults = commentResults;
    }
}
