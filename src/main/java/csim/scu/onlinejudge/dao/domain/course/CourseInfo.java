package csim.scu.onlinejudge.dao.domain.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class CourseInfo {

    private Long id;
    private String name;
    private String semester;
}
