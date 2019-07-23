package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.manager.CourseManager;
import csim.scu.onlinejudge.manager.ProblemManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "CourseApi", description = "課程的相關Api")
@RequestMapping("/api/course")
@RestController
public class CourseApi extends BaseApi {

    private CourseManager courseManager;
    private ProblemManager problemManager;

    @Autowired
    public CourseApi(CourseManager courseManager,
                     ProblemManager problemManager) {
        this.courseManager = courseManager;
        this.problemManager = problemManager;
    }

    @ApiOperation(value = "取得所有課程資訊",
            notes = "取得所有課程資訊")
    @GetMapping(value = "/getCourses")
    private Message getCourses(HttpSession session) {
        Message message;
        String account = getUserAccount(session);
        try {
            message = new Message(ApiMessageCode.SUCCESS_STATUS, courseManager.getCoursesInfo(account));
        } catch (Exception e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.GET_COURSES_INFO_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "取得課程裡的學生所有資訊",
            notes = "取得courseId，來獲得課程裡的學生所有資訊")
    @GetMapping(value = "/getStudentsData")
    private Message getStudentInfo(String courseId) {
        Message message;
        try {
            message = new Message(ApiMessageCode.SUCCESS_STATUS, problemManager.getStudentsData(Long.parseLong(courseId)));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.GET_STUDENT_DATA_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "刪除課程",
            notes = "取得courseId，並刪除課程")
    @PostMapping(value = "/delCourse")
    private Message delCourse(@RequestBody Map<String, String> map) {
        Message message;
        String courseId = map.get("courseId");
        try {
            courseManager.deleteCourseById(Long.parseLong(courseId));
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.DEL_COURSE_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "編輯課程",
            notes = "取得courseId，並編輯課程")
    @PostMapping(value = "/editCourse")
    private Message editCourse(@RequestBody Map<String, Object> map, HttpSession session) {
        Message message;
        String courseId = (String) map.get("courseId");
        String account = getUserAccount(session);
        String courseName = (String) map.get("courseName");
        String semester = (String) map.get("semester");
        List<String> taList = (List<String>) map.get("taList");
        try {
            courseManager.editCourse(account, Long.parseLong(courseId), courseName, semester, taList);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.EDIT_COURSE_ERROR, "");
        }
        return message;
    }

}
