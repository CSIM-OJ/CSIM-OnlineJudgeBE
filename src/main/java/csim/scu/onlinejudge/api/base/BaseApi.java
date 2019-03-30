package csim.scu.onlinejudge.api.base;

import csim.scu.onlinejudge.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BaseApi {

    private final String LOGGED_IN = "logged_in";
    private final String USER_TYPE = "user_type";

    protected AdminService adminService;
    protected AssistantService assistantService;
    protected CommonService commonService;
    protected CopyService copyService;
    protected CourseService courseService;
    protected FeedbackService feedbackService;
    protected JudgeService judgeService;
    protected ProblemService problemService;
    protected StudentService studentService;
    protected TeacherService teacherService;
    protected TeamService teamService;

    @Autowired
    private void setAllService(AdminService adminService,
                          AssistantService assistantService,
                          CommonService commonService,
                          CopyService copyService,
                          CourseService courseService,
                          FeedbackService feedbackService,
                          JudgeService judgeService,
                          ProblemService problemService,
                          StudentService studentService,
                          TeacherService teacherService,
                          TeamService teamService) {
        this.adminService = adminService;
        this.assistantService = assistantService;
        this.commonService = commonService;
        this.copyService = copyService;
        this.courseService = courseService;
        this.feedbackService = feedbackService;
        this.judgeService = judgeService;
        this.problemService = problemService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.teamService = teamService;
    }

    protected void setUserAccount(String account, HttpSession session) {
        session.setAttribute(LOGGED_IN, account);
    }

    protected String getUserAccount(HttpSession session) {
        return session.getAttribute(LOGGED_IN).toString();
    }

    protected String getUserType(HttpSession session) {
        return session.getAttribute(USER_TYPE).toString();
    }

    protected void setUserType(String type, HttpSession session) {
        session.setAttribute(USER_TYPE, type);
    }

    protected boolean isLogin(HttpSession session) {
        return session.getAttribute(LOGGED_IN) != null;
    }

    protected void destroySession(HttpSession session) {
        session.invalidate();
    }
}
