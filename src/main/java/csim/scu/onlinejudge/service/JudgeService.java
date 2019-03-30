package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.JudgeNotFoundException;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.student.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JudgeService {

    int countByProblemAndStudent(Problem problem, Student student);
    Judge findByProblemAndStudent(Problem problem, Student student) throws JudgeNotFoundException;
    Judge save(Judge judge);
    boolean existByProblemAndStudent(Problem problem, Student student);
    List<Judge> findByProblem(Problem problem);
    int updateRateByProblemAndStudent(double rate, Problem problem, Student student);
    double getAvgRateByProblem(Problem problem);
    List<Judge> findByStudent(Student student);
}
