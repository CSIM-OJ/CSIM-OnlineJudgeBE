package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.course.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseName(String name);
    @Query(value = "select new csim.scu.onlinejudge.dao.domain.course.CourseInfo(c.courseId, c.courseName, c.semester) from Course c")
    List<CourseInfo> getAllCourseInfo();
}
