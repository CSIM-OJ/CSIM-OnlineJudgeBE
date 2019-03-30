package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.ProblemNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.problem.ProblemInfo;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.dao.repository.ProblemRepository;
import csim.scu.onlinejudge.service.ProblemService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    private ProblemRepository problemRepository;

    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Transactional
    @Override
    public Problem save(Problem problem) {
        return problemRepository.save(problem);
    }

    @Override
    public Problem findById(Long id) throws ProblemNotFoundException {
        return problemRepository.findById(id).orElseThrow(ProblemNotFoundException::new);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Long problemId, String name, String type,
                          String category, String[] tag,
                          String description, String inputDesc,
                          String outputDesc, List<TestCase> testCases,
                          Date deadline) throws ProblemNotFoundException {
        Problem problem = findById(problemId);
        problem.setName(name);
        problem.setType(type);
        problem.setCategory(category);
        problem.setTag(tag);
        problem.setDescription(description);
        problem.setInputDesc(inputDesc);
        problem.setOutputDesc(outputDesc);
        problem.setTestCases(testCases);
        problem.setDeadline(deadline);
        problemRepository.save(problem);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        problemRepository.deleteById(id);
    }

    @Override
    public List<Problem> findByCourse(Course course) {
        return problemRepository.findByCourse(course);
    }

    @Override
    public ProblemInfo getInfo(Long problemId) throws ProblemNotFoundException {
        Optional<ProblemInfo> instance = problemRepository.findByProblemId(problemId);
        ProblemInfo problemInfo = instance.orElseThrow(ProblemNotFoundException::new);
        return problemInfo;
    }

    @Override
    public List<Problem> findByType(String type) {
        return problemRepository.findByType(type);
    }

    @Override
    public List<Problem> findByCourseAndType(Course course, String type) {
        return problemRepository.findByCourseAndType(course, type);
    }

    @Transactional
    @Override
    public int updateRateByProblemId(Long problemId, double rate) {
        return problemRepository.updateRateByProblemId(problemId, rate);
    }

    @Override
    public int countByBestStudentAccountAndCourse(String account, Course course) {
        return problemRepository.countByBestStudentAccountAndCourse(account, course);
    }

}
