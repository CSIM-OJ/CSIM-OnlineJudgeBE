package csim.scu.onlinejudge.dao.domain.teacher;

import csim.scu.onlinejudge.dao.domain.course.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String account;
    private String password;
    private String name;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;

    public Teacher(String account, String password, String name, List<Course> courses) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.courses = courses;
    }

}
