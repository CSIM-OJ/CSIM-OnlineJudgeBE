package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.StudentNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.dao.repository.StudentRepository;
import csim.scu.onlinejudge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean existByAccount(String account) {
        return studentRepository.existsByAccount(account);
    }

    @Override
    public Student findByAccount(String account) throws StudentNotFoundException {
        return studentRepository.findByAccount(account).orElseThrow(StudentNotFoundException::new);
    }

    @Transactional
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public int updatePasswordByAccount(String account, String oriPassword, String newPassword) {
        return studentRepository.updatePasswordByAccountAndPassword(account, oriPassword, newPassword);
    }

    @Override
    public List<Course> findCoursesByAccount(String account) throws StudentNotFoundException {
        Student student = findByAccount(account);
        return student.getCourses();
    }

    @Override
    public List<Map<String, String>> getCourseIdAndCourseName(String account) throws StudentNotFoundException {
        List<Map<String, String>> list = new ArrayList<>();
        List<Course> courses = findCoursesByAccount(account);
        for (Course course : courses) {
            Map<String, String> map = new HashMap<>();
            map.put("courseId", course.getCourseId().toString());
            map.put("courseName", course.getCourseName());
            list.add(map);
        }
        return list;
    }
}
