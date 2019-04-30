package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.course.CourseInfo;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends BaseRepository<Course, Long> {

    Optional<Course> findByName(String name);

    @Query(value = "select new csim.scu.onlinejudge.dao.domain.course.CourseInfo(c.id, c.name, c.semester) from Course c")
    List<CourseInfo> getAllCourseInfo();
}
