package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.TeacherNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.teacher.Teacher;
import csim.scu.onlinejudge.dao.repository.TeacherRepository;
import csim.scu.onlinejudge.service.CourseService;
import csim.scu.onlinejudge.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public boolean existByAccount(String account) {
        return teacherRepository.existsByAccount(account);
    }

    @Override
    public Teacher findByAccount(String account) throws TeacherNotFoundException {
        return teacherRepository.findByAccount(account).orElseThrow(TeacherNotFoundException::new);
    }

    @Override
    public int updatePasswordByAccount(String account, String oriPassword, String newPassword) {
        return teacherRepository.updatePasswordByAccountAndPassword(account, oriPassword, newPassword);
    }

    @Override
    public List<Course> findCoursesByAccount(String account) throws TeacherNotFoundException {
        return findByAccount(account).getCourses();
    }

    @Override
    public List<Map<String, String>> getCoursesInfo(String account) throws TeacherNotFoundException {
        List<Map<String, String>> results = new ArrayList<>();
        List<Course> courses = findCoursesByAccount(account);

        for (Course course : courses) {
            Map<String, String> map = new HashMap<>();
            map.put("courseId", String.valueOf(course.getCourseId()));
            map.put("courseName", course.getCourseName());
            map.put("semester", course.getSemester());
            results.add(map);
        }
        return results;
    }

}
