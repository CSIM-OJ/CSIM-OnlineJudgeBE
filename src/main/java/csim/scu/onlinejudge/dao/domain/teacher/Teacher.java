package csim.scu.onlinejudge.dao.domain.teacher;

import csim.scu.onlinejudge.dao.domain.base.AbstractUser;
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
public class Teacher extends AbstractUser {

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;

    public Teacher(String account, String password, String name, List<Course> courses) {
        super(account, password, name);
        this.courses = courses;
    }

}
