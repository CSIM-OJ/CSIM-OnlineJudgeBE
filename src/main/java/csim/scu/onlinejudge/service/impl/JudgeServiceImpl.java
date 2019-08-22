package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.dao.repository.JudgeRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.JudgeService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeServiceImpl extends BaseServiceImpl<Judge, Long> implements JudgeService {

    private JudgeRepository judgeRepository;

    @Autowired
    public JudgeServiceImpl(JudgeRepository judgeRepository) {
        this.judgeRepository = judgeRepository;
    }

    @Override
    public BaseRepository<Judge, Long> getBaseRepository() {
        return judgeRepository;
    }

    @Override
    public int countByProblemAndStudent(Problem problem, Student student) {
        return judgeRepository.countByProblemAndStudent(problem, student);
    }

    @Override
    public Judge findByProblemAndStudent(Problem problem, Student student) throws EntityNotFoundException {
        return judgeRepository.findByProblemAndStudent(problem, student).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean existByProblemAndStudent(Problem problem, Student student) {
        return judgeRepository.existsByProblemAndStudent(problem, student);
    }

    @Override
    public List<Judge> findByProblem(Problem problem) {
        return judgeRepository.findByProblem(problem);
    }

    @Override
    public int updateRateByProblemAndStudent(double rate, Problem problem, Student student) {
        return judgeRepository.updateRateByProblemAndStudent(rate, problem, student);
    }

    @Override
    public double getAvgRateByProblem(Problem problem) {
        return judgeRepository.getAvgRateByProblem(problem);
    }

    @Override
    public List<Judge> findByStudent(Student student) {
        return judgeRepository.findByStudent(student);
    }

    @Override
    public int countByProblem(Problem problem) {
        return judgeRepository.countByProblem(problem);
    }

    @Override
    public List<Judge> findByCourseAndStudent(Course course, Student student) {
        return judgeRepository.findByCourseAndStudent(course, student);
    }
}
