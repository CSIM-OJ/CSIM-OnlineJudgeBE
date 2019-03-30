package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.problem.ProblemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    Optional<Problem> findByName(String name);
    void deleteByName(String name);
    List<Problem> findByCourse(Course course);
    Optional<ProblemInfo> findByProblemId(Long id);
    List<Problem> findByType(String type);
    List<Problem> findByCourseAndType(Course course, String type);
    @Modifying(clearAutomatically = true)
    @Query("update Problem set rate=:rate where problemId=:problemId")
    int updateRateByProblemId(@Param("problemId") Long problemId,
                   @Param("rate") double rate);
    int countByBestStudentAccountAndCourse(String account, Course course);
}
