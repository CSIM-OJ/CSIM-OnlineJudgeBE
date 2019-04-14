package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.course.CourseInfo;
import csim.scu.onlinejudge.dao.repository.CourseRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.CourseService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, Long> implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public BaseRepository<Course, Long> getBaseRepository() {
        return courseRepository;
    }

    @Override
    public List<CourseInfo> getAllCourseInfo() {
        return courseRepository.getAllCourseInfo();
    }

}
