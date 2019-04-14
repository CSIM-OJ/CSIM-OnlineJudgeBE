package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.service.base.BaseService;

import java.util.List;

public interface JudgeService extends BaseService<Judge, Long> {

    int countByProblemAndStudent(Problem problem, Student student);

    Judge findByProblemAndStudent(Problem problem, Student student) throws EntityNotFoundException;

    boolean existByProblemAndStudent(Problem problem, Student student);

    List<Judge> findByProblem(Problem problem);

    int updateRateByProblemAndStudent(double rate, Problem problem, Student student);

    double getAvgRateByProblem(Problem problem);

    List<Judge> findByStudent(Student student);
}
