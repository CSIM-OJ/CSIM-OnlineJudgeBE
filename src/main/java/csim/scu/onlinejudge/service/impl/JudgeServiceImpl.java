package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.JudgeNotFoundException;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.dao.repository.JudgeRepository;
import csim.scu.onlinejudge.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JudgeServiceImpl implements JudgeService {

    private JudgeRepository judgeRepository;

    @Autowired
    public JudgeServiceImpl(JudgeRepository judgeRepository) {
        this.judgeRepository = judgeRepository;
    }

    @Override
    public int countByProblemAndStudent(Problem problem, Student student) {
        return judgeRepository.countByProblemAndStudent(problem, student);
    }

    @Override
    public Judge findByProblemAndStudent(Problem problem, Student student) throws JudgeNotFoundException {
        return judgeRepository.findByProblemAndStudent(problem, student).orElseThrow(JudgeNotFoundException::new);
    }

    @Override
    public Judge save(Judge judge) {
        return judgeRepository.save(judge);
    }

    @Override
    public boolean existByProblemAndStudent(Problem problem, Student student) {
        return judgeRepository.existsByProblemAndStudent(problem, student);
    }

    @Override
    public List<Judge> findByProblem(Problem problem) {
        return judgeRepository.findByProblem(problem);
    }

    @Transactional
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
}
