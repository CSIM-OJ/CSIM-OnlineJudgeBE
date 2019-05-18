package csim.scu.onlinejudge.dao.domain.problembank;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import csim.scu.onlinejudge.dao.domain.base.BaseEntity;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class)})
@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProblemBank extends BaseEntity{

    private String name;
    private String type;
    private String category;
    @Type(type = "string-array")
    @Column(name = "tag", columnDefinition = "text[]")
    private String[] tag;
    private String description;
    private String inputDesc;
    private String outputDesc;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<TestCase> testCases;

    public ProblemBank(String name, String type, String category, String[] tag, String description, String inputDesc, String outputDesc, List<TestCase> testCases) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.tag = tag;
        this.description = description;
        this.inputDesc = inputDesc;
        this.outputDesc = outputDesc;
        this.testCases = testCases;
    }
}
