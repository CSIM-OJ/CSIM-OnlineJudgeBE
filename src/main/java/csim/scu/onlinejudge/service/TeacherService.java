package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.TeacherNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.teacher.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    boolean existByAccount(String account);
    Teacher findByAccount(String account) throws TeacherNotFoundException;
    int updatePasswordByAccount(String account, String oriPassword, String newPassword);
    List<Course> findCoursesByAccount(String account) throws TeacherNotFoundException;
    List<Map<String, String>> getCoursesInfo(String account) throws TeacherNotFoundException;
}
