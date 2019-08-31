package com.penguin.penguincoco.dao;

import com.penguin.penguincoco.penguinCocoApplicationTests;
import com.penguin.penguincoco.dao.domain.teacher.Teacher;
import com.penguin.penguincoco.dao.domain.course.Course;
import com.penguin.penguincoco.dao.repository.CourseRepository;
import com.penguin.penguincoco.dao.repository.TeacherRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class CourseDaoTest extends penguinCocoApplicationTests implements BaseDao {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    private Teacher teacher;

    @Before
    public void setUp() {
        teacher = new Teacher("666666", "0000", "教授", new ArrayList<>());
        teacherRepository.save(teacher);
    }

    @Override
    @Test
    @Transactional
    public void save() {
        Course course = new Course(teacher, "計算機程式設計",
                "104上", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        courseRepository.save(course);
    }

    @Override
    @Test
    @Transactional
    public void find() {
        Course expected = new Course(teacher, "計算機程式設計",
                "104上", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        courseRepository.save(expected);
        Course result = courseRepository.findByName("計算機程式設計").get();
        assertEquals(expected, result);
    }

    @Override
    @Test
    @Transactional
    public void delete() {
        Course expected = new Course(teacher, "計算機程式設計",
                "104上", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        courseRepository.save(expected);
        courseRepository.deleteById(expected.getId());
    }
}
