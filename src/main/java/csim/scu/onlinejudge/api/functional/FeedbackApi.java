package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.exception.CourseNotFoundException;
import csim.scu.onlinejudge.common.exception.StudentNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.dao.domain.feedback.Feedback;
import csim.scu.onlinejudge.service.CommonService;
import csim.scu.onlinejudge.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Api(value = "FeedbackApi", description = "回饋的相關Api")
@RequestMapping("/api/feedback")
@RestController
public class FeedbackApi extends BaseApi {

    @ApiOperation(value = "新增回饋",
            notes = "取得courseId、content，並新增回饋")
    @PostMapping(value = "/addFeedback")
    private Message addFeedback(@RequestBody Map<String, String> map, HttpSession session) {
        Message message;

        String courseId = map.get("courseId");
        String content = map.get("content");
        String account = getUserAccount(session);
        try {
            commonService.addFeedback(Long.parseLong(courseId), account, content);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (ParseException | CourseNotFoundException | StudentNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.ADD_FEEDBACK_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "取得課程下的所有回饋",
            notes = "取得courseId，並獲取課程下的所有回饋")
    @GetMapping(value = "/getCourseFeedbacks")
    private Message getCourseFeedbacks(String courseId) {
        Message message;
        try {
            List<Feedback> feedbacks = commonService.findFeedbacksByCourseId(Long.parseLong(courseId));
            message = new Message(ApiMessageCode.SUCCESS_STATUS, feedbacks);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.GET_COURSE_FEEDBACK_ERROR, "");
        }
        return message;
    }
}
