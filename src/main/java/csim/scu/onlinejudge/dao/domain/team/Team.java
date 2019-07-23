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
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "string-array", typeClass = StringArrayType.class)})
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "problem_id")
    private Problem problem;
    private String account;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<String> correctedAccount;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<CommentResult> commentResult;

    public Team(Problem problem, String account, List<String> correctedAccount, List<CommentResult> commentResult) {
        this.problem = problem;
        this.account = account;
        this.correctedAccount = correctedAccount;
        this.commentResult = commentResult;
    }
}
