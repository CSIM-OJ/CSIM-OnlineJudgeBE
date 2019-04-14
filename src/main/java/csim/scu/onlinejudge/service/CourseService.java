package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.course.CourseInfo;
import csim.scu.onlinejudge.service.base.BaseService;

import java.util.List;

public interface CourseService extends BaseService<Course, Long> {

    List<CourseInfo> getAllCourseInfo();
}
