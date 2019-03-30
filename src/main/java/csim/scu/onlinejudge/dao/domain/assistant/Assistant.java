package csim.scu.onlinejudge.dao.domain.assistant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import csim.scu.onlinejudge.dao.domain.course.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Assistant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assistantId;
    private String account;
    private String password;
    private String name;
    @ManyToMany
    @JoinTable(name = "assistant_course", joinColumns = @JoinColumn(name = "assistant_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnoreProperties("assistants")
    private List<Course> courses;

    public Assistant(String account, String password, String name, List<Course> courses) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.courses = courses;
    }

}
