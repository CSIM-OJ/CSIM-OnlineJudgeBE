package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.teacher.Teacher;
import csim.scu.onlinejudge.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface TeacherService extends BaseService<Teacher, Long> {

    boolean existByAccount(String account);

    Teacher findByAccount(String account) throws EntityNotFoundException;

    int updatePasswordByAccount(String account, String oriPassword, String newPassword);

    List<Course> findCoursesByAccount(String account) throws EntityNotFoundException;

}
