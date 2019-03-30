package csim.scu.onlinejudge.api.functional;


import csim.scu.onlinejudge.api.base.BaseMethod;
import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.exception.*;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.service.CommonService;
import csim.scu.onlinejudge.service.CourseService;
import csim.scu.onlinejudge.service.ProblemService;
import csim.scu.onlinejudge.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Api(value = "TeacherApi", description = "教師的相關Api")
@RequestMapping("/api/teacher")
@RestController
public class TeacherApi extends BaseApi {

    @ApiOperation(value = "建立課程",
                  notes = "取得courseName、semester並建立課程")
    @PostMapping(value = "/createCourse")
    private Message createCourse(@RequestBody Map<String, String> map,
                                 HttpSession session) {
        Message message;
        String account = getUserAccount(session);
        String courseName = map.get("courseName");
        String semester = map.get("semester");
        try {
            commonService.createCourse(account, courseName, semester);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (TeacherNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.CREATE_COURSE_ERROR, "");

        }
        return message;
    }

    @ApiOperation(value = "刪除課程",
            notes = "取得courseId，並刪除課程")
    @PostMapping(value = "/deleteCourse")
    private Message deleteCourse(@RequestBody Map<String, String> map) {
        String courseId = map.get("courseId");
        courseService.deleteCourseById(Long.parseLong(courseId));
        Message message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        return message;
    }

    @ApiOperation(value = "將學生列表加入課程",
            notes = "取得courseId、accountList，並加入課程")
    @PostMapping(value = "/addStudentList")
    private Message addStudentList(@RequestBody Map<String, Object> map) {
        return BaseMethod.addStudentList(map, commonService);
    }

    @ApiOperation(value = "將學生列表退出課程",
            notes = "取得courseId、accountList，並退出課程")
    @PostMapping(value = "/deleteStudentList")
    private Message deleteStudentList(@RequestBody Map<String, Object> map) {
        return BaseMethod.deleteStudentList(map, commonService);
    }

    @ApiOperation(value = "將助教列表加入課程",
            notes = "取得courseId、accountList，並加入課程")
    @PostMapping(value = "/addAssistantList")
    private Message addAssistantList(@RequestBody Map<String, Object> map) {
        Message message;
        String courseId = map.get("courseId").toString();
        List<String> accountList = (List<String>) map.get("accountList");
        try {
            commonService.mapAssistantListToCourse(Long.parseLong(courseId), accountList);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (AssistantNotFoundException | CourseNotFoundException e) {
            message = new Message(ApiMessageCode.MAP_ASSISTANTLIST_COURSE_ERROR, "");
            e.printStackTrace();
        }
        return message;
    }

    @ApiOperation(value = "將助教列表退出課程",
            notes = "取得courseId、accountList，並退出課程")
    @PostMapping(value = "/deleteAssistantList")
    private Message deleteAssistantList(@RequestBody Map<String, Object> map) {
        Message message;
        String courseId = map.get("courseId").toString();
        List<String> accountList = (List<String>) map.get("accountList");
        try {
            commonService.deleteAssistantListFromCourse(Long.parseLong(courseId), accountList);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (AssistantNotFoundException | CourseNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.DELETE_ASSISTANTLIST_COURSE_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "取得老師的所有課程",
            notes = "取得account，來獲得老師的所有課程")
    @GetMapping(value = "/courseList")
    private Message getCourseList(HttpSession session) {
        Message message;

        String account = getUserAccount(session);
        try {
            message = new Message(ApiMessageCode.SUCCESS_STATUS, teacherService.getCoursesInfo(account));
        } catch (TeacherNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.GET_COURSE_INFO_ERROR, "");
        }
        return message;
    }
}
