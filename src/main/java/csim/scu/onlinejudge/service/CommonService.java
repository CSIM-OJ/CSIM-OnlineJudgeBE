package csim.scu.onlinejudge.service;

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

public interface CommonService {

    String findUserAuthority(String account, String password) throws StudentNotFoundException, TeacherNotFoundException, AssistantNotFoundException, AdminNotFoundException;

    int updateUserPassword(String account, String oriPassword, String newPassword, String userType);

    Course createCourse(String account, String courseName, String semester) throws TeacherNotFoundException;

    void mapStudentListToCourse(Long courseId, List<String> accounts) throws StudentNotFoundException, CourseNotFoundException;

    void deleteStudentListFromCourse(Long courseId, List<String> accounts) throws StudentNotFoundException, CourseNotFoundException, JudgeNotFoundException;

    void mapAssistantListToCourse(Long courseId, List<String> accounts) throws AssistantNotFoundException, CourseNotFoundException;

    void deleteAssistantListFromCourse(Long courseId, List<String> accounts) throws AssistantNotFoundException, CourseNotFoundException;

    void createProblem(Long courseId, String name, String type,
                          String category, String[] tag, String description,
                          String inputDesc, String outputDesc,
                          List<TestCase> testCases, Date deadline) throws CourseNotFoundException;

    List<Problem> findByCourseId(Long courseId) throws CourseNotFoundException;

    Map<String, String> findStudentCourseInfo(Long courseId, String account) throws StudentNotFoundException, JudgeNotFoundException, CourseNotFoundException;

    void judgeCode(Long problemId, String code, String language, String account) throws ProblemNotFoundException, StudentNotFoundException, JudgeNotFoundException;

    Judge findByProblemIdAndStudentAccount(Long problemId, String account) throws ProblemNotFoundException, StudentNotFoundException, JudgeNotFoundException;

    boolean existByProblemIdAndStudentAccount(Long problemId, String account) throws ProblemNotFoundException, StudentNotFoundException;

    void judgeCopy(Long problemId) throws ProblemNotFoundException;

    Feedback addFeedback(Long courseId, String account, String content) throws ParseException, CourseNotFoundException, StudentNotFoundException;

    List<Feedback> findFeedbacksByCourseId(Long courseId) throws CourseNotFoundException;

    List<Map<String, String>> getStudentProblemInfo(Long courseId, String type, boolean isJudge, String account) throws CourseNotFoundException, StudentNotFoundException;

    int updateJudgeRateByProblemIdAndAccount(double rate, Long problemId, String account) throws ProblemNotFoundException, StudentNotFoundException;

    List<Map<String, String>> getStudentCoursesInfo(String account) throws StudentNotFoundException;

    List<Map<String, String>> getCoursesInfo();

    List<Map<String, Object>> getStudentHistoryScoreInfo(Long courseId, String account) throws CourseNotFoundException, JudgeNotFoundException, StudentNotFoundException;

    List<CorrectRank> getCorrectRank(Long courseId) throws CourseNotFoundException;

    List<BestCodeRank> getBestCodeRank(Long courseId) throws CourseNotFoundException;
}
