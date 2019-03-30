package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.StudentNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.student.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    boolean existByAccount(String account);
    Student findByAccount(String account) throws StudentNotFoundException;
    Student save(Student student);
    int updatePasswordByAccount(String account, String oriPassword, String newPassword);
    List<Course> findCoursesByAccount(String account) throws StudentNotFoundException;
    List<Map<String, String>> getCourseIdAndCourseName(String account) throws StudentNotFoundException;
}
