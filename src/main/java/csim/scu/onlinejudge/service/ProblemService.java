package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.ProblemNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.problem.ProblemInfo;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;

import java.util.Date;
import java.util.List;

public interface ProblemService {

    Problem save(Problem problem);
    Problem findById(Long id) throws ProblemNotFoundException;
    void update(Long problemId, String name, String type,
                   String category, String[] tag, String description,
                   String inputDesc, String outputDesc,
                   List<TestCase> testCases, Date deadline) throws ProblemNotFoundException;
    void deleteById(Long id);
    List<Problem> findByCourse(Course course);
    ProblemInfo getInfo(Long problemId) throws ProblemNotFoundException;
    List<Problem> findByType(String type);
    List<Problem> findByCourseAndType(Course course, String type);
    int updateRateByProblemId(Long problemId, double rate);
    int countByBestStudentAccountAndCourse(String account, Course course);
}
