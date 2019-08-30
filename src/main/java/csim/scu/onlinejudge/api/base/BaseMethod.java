package csim.scu.onlinejudge.api.base;

import csim.scu.onlinejudge.common.exception.EntityExistsException;
import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.manager.CourseManager;

import java.util.List;
import java.util.Map;

public class BaseMethod {

    public static Message addStudentList(Map<String, Object> map,
                                  CourseManager courseManager) {
        Message message;
        String courseId = map.get("courseId").toString();
        List<String> accountList = (List<String>) map.get("accountList");
        try {
            courseManager.mapStudentListToCourse(Long.parseLong(courseId), accountList);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.MAP_STUDENTLIST_COURSE_ERROR_1, "");
        } catch (EntityExistsException e) {
            message = new Message(ApiMessageCode.MAP_STUDENTLIST_COURSE_ERROR_2, "");
        }
        return message;
    }

    public static Message deleteStudentList(Map<String, Object> map,
                                            CourseManager courseManager) {
        Message message;
        String courseId = map.get("courseId").toString();
        List<String> accountList = (List<String>) map.get("accountList");
        try {
            courseManager.deleteStudentListFromCourse(Long.parseLong(courseId), accountList);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.DELETE_STUDENTLIST_COURSE_ERROR, "");
        }
        return message;
    }
}
