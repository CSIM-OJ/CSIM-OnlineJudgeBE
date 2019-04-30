package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.manager.CourseManager;
import csim.scu.onlinejudge.manager.ProblemManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private Message getCourses() {
        Message message;
        try {
            message = new Message(ApiMessageCode.SUCCESS_STATUS, courseManager.getCoursesInfo());
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
}
