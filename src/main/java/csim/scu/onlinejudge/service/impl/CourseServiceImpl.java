package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.CourseNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.course.CourseInfo;
import csim.scu.onlinejudge.dao.domain.teacher.Teacher;
import csim.scu.onlinejudge.dao.repository.CourseRepository;
import csim.scu.onlinejudge.service.CourseService;
import csim.scu.onlinejudge.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course findById(Long id) throws CourseNotFoundException {
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    @Transactional
    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseInfo> getAllCourseInfo() {
        return courseRepository.getAllCourseInfo();
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
