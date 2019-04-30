package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface StudentService extends BaseService<Student, Long> {

    boolean existByAccount(String account);

    Student findByAccount(String account) throws EntityNotFoundException;

    int updatePasswordByAccount(String account, String oriPassword, String newPassword);

    List<Course> findCoursesByAccount(String account) throws EntityNotFoundException;

    List<Map<String, String>> getCourseIdAndCourseName(String account) throws EntityNotFoundException;

    List<Student> findByStudentClass(String studentClass);

    List<String> findDistinctStudentClass();
}
