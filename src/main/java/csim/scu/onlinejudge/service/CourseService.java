package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.CourseNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.course.CourseInfo;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course findById(Long id) throws CourseNotFoundException;
    Course save(Course course);
    void deleteCourseById(Long id);
    List<CourseInfo> getAllCourseInfo();
    List<Course> findAll();
}
