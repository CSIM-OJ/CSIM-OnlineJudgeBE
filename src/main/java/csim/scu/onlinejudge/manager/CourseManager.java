package csim.scu.onlinejudge.manager;

import csim.scu.onlinejudge.common.exception.*;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.feedback.Feedback;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.dao.domain.student.BestCodeRank;
import csim.scu.onlinejudge.dao.domain.student.CorrectRank;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CourseManager {

    Course createCourse(String account, String courseName, String semester, String studentClass, List<String> taList) throws EntityNotFoundException;

    void deleteCourseById(Long id) throws EntityNotFoundException;

    void mapStudentListToCourse(Long courseId, List<String> accounts) throws EntityNotFoundException;

    void deleteStudentListFromCourse(Long courseId, List<String> accounts) throws EntityNotFoundException;

    void mapAssistantListToCourse(Long courseId, List<String> accounts) throws EntityNotFoundException;

    void deleteAssistantListFromCourse(Long courseId, List<String> accounts) throws EntityNotFoundException;

    List<Map<String, String>> getCoursesInfo(String account) throws EntityNotFoundException;

    void createProblem(Long courseId, String name, String type,
                       String category, String[] tag, String description,
                       String inputDesc, String outputDesc,
                       String[] keyword, String[] pattern,
                       List<TestCase> testCases, Date deadline) throws EntityNotFoundException;

    List<Problem> findByCourseId(Long courseId) throws EntityNotFoundException;

    Map<String, String> findStudentCourseInfo(Long courseId, String account) throws EntityNotFoundException;

    Feedback addFeedback(Long courseId, String account, String content) throws ParseException, EntityNotFoundException;

    List<Map<String, String>> findFeedbacksByCourseId(Long courseId) throws EntityNotFoundException;

    List<Map<String, String>> getStudentCoursesInfo(String account) throws EntityNotFoundException;

    List<Map<String, String>> getCoursesInfo();
}
