package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.service.CommonService;
import csim.scu.onlinejudge.service.CourseService;
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

    @ApiOperation(value = "取得所有課程資訊",
            notes = "取得所有課程資訊")
    @GetMapping(value = "/getCourses")
    private Message getCourses() {
        Message message;
        try {
            message = new Message(ApiMessageCode.SUCCESS_STATUS, commonService.getCoursesInfo());
        } catch (Exception e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.GET_COURSES_INFO_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "取得課程裡的學生所有資訊",
            notes = "取得courseId，來獲得課程裡的學生所有資訊")
    @GetMapping(value = "/getStudentInfo")
    private Message getStudentInfo() {
        Message message;
        return null;
    }
}
