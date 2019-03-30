package csim.scu.onlinejudge.dao.domain.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.feedback.Feedback;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String account;
    private String password;
    private String name;
    private String studentClass;
    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnoreProperties("students")
    private List<Course> courses;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Judge> judges;

    public Student(String account, String password, String name, String studentClass, List<Course> courses, List<Feedback> feedbacks, List<Judge> judges) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.studentClass = studentClass;
        this.courses = courses;
        this.feedbacks = feedbacks;
        this.judges = judges;
    }

}
