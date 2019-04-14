package csim.scu.onlinejudge.manager.impl;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.manager.ProblemManager;
import csim.scu.onlinejudge.service.CourseService;
import csim.scu.onlinejudge.service.JudgeService;
import csim.scu.onlinejudge.service.ProblemService;
import csim.scu.onlinejudge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProblemManagerImpl implements ProblemManager {

    private ProblemService problemService;
    private CourseService courseService;
    private JudgeService judgeService;
    private StudentService studentService;

    @Autowired
    public ProblemManagerImpl(ProblemService problemService,
                              CourseService courseService,
                              JudgeService judgeService,
                              StudentService studentService) {
        this.problemService = problemService;
        this.courseService = courseService;
        this.judgeService = judgeService;
        this.studentService = studentService;
    }

    // 根據課程Id、學生account，取得學生擁有的題目資訊(含作答及未作答)
    @Override
    public List<Map<String, String>> getStudentProblemInfo(Long courseId, String type, boolean isJudge, String account) throws EntityNotFoundException {
        Course course = courseService.findById(courseId);
        Student student = studentService.findByAccount(account);
        List<Problem> problems;
        List<Problem> realProblems = new ArrayList<>();
        if (type.equals("全部")) {
            problems = problemService.findByCourse(course);
        }
        else {
            problems = problemService.findByCourseAndType(course, type);
        }
        if (isJudge) {
            for (Problem problem : problems) {
                boolean isExist = judgeService.existByProblemAndStudent(problem, student);
                if (isExist) {
                    realProblems.add(problem);
                }
            }
        }
        else {
            for (Problem problem : problems) {
                boolean isExist = judgeService.existByProblemAndStudent(problem, student);
                if (!isExist) {
                    realProblems.add(problem);
                }
            }
        }
        List<Map<String, String>> results = new ArrayList<>();
        for (Problem problem : realProblems) {
            Map<String, String> map = new HashMap<>();
            map.put("problemId", String.valueOf(problem.getId()));
            map.put("name", problem.getName());
            map.put("type", problem.getType());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            map.put("deadline", df.format(problem.getDeadline()));
            map.put("rate", String.valueOf(problem.getRate()));
            results.add(map);
        }
        return results;
    }
}
