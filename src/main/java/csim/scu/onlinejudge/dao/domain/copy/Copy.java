package csim.scu.onlinejudge.dao.domain.copy;

import csim.scu.onlinejudge.dao.domain.problem.Problem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "problem_id")
    private Problem problem;
    private String studentOneAccount;
    private String studentTwoAccount;
    private double similarity;

    public Copy(Problem problem, String studentOneAccount, String studentTwoAccount, double similarity) {
        this.problem = problem;
        this.studentOneAccount = studentOneAccount;
        this.studentTwoAccount = studentTwoAccount;
        this.similarity = similarity;
    }
}
