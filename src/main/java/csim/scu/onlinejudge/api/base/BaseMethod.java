package csim.scu.onlinejudge.api.base;

import csim.scu.onlinejudge.common.exception.CourseNotFoundException;
import csim.scu.onlinejudge.common.exception.JudgeNotFoundException;
import csim.scu.onlinejudge.common.exception.StudentNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.service.CommonService;

import java.util.List;
import java.util.Map;

public class BaseMethod {

    public static Message addStudentList(Map<String, Object> map,
                                  CommonService commonService) {
        Message message;
        String courseId = map.get("courseId").toString();
        List<String> accountList = (List<String>) map.get("accountList");
        try {
            commonService.mapStudentListToCourse(Long.parseLong(courseId), accountList);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (StudentNotFoundException | CourseNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.MAP_STUDENTLIST_COURSE_ERROR, "");
        }
        return message;
    }

    public static Message deleteStudentList(Map<String, Object> map, CommonService commonService) {
        Message message;
        String courseId = map.get("courseId").toString();
        List<String> accountList = (List<String>) map.get("accountList");
        try {
            commonService.deleteStudentListFromCourse(Long.parseLong(courseId), accountList);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (StudentNotFoundException | CourseNotFoundException | JudgeNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.DELETE_STUDENTLIST_COURSE_ERROR, "");
        }
        return message;
    }
}
