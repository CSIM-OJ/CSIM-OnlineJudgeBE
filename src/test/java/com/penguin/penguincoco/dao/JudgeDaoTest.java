package com.penguin.penguincoco.dao;

import com.penguin.penguincoco.penguinCocoApplicationTests;
import com.penguin.penguincoco.dao.domain.course.Course;
import com.penguin.penguincoco.dao.domain.judge.Judge;
import com.penguin.penguincoco.dao.domain.problem.Problem;
import com.penguin.penguincoco.dao.domain.problem.TestCase;
import com.penguin.penguincoco.dao.domain.student.Student;
import com.penguin.penguincoco.dao.domain.teacher.Teacher;
import com.penguin.penguincoco.dao.domain.judge.HistoryCode;
import com.penguin.penguincoco.dao.repository.*;
import com.penguin.penguincoco.dao.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JudgeDaoTest extends penguinCocoApplicationTests implements BaseDao {

    @Autowired
    private JudgeRepository judgeRepository;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    private Problem problem;
    private Student student;

    @Before
    public void setUp() {
        student = new Student("04156199", "0000",
                "Jack", "104資管B", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        studentRepository.save(student);
        Teacher teacher = new Teacher("666666", "0000", "教授", new ArrayList<>());
        teacherRepository.save(teacher);
        Course course = new Course(teacher, "計算機程式設計",
                "104上", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        courseRepository.save(course);

        List<TestCase> testCases = Arrays.asList(
                new TestCase("123", "123"),
                new TestCase("456", "456"),
                new TestCase("789", "789")
        );
        String deadline = "2019-02-17";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(deadline);
            problem = new Problem(course, "計算速率",
                    "作業", "輸入輸出",
                    new String[]{"Java","條件","迴圈"},
                    0,  "描述", "輸入描述",
                    "輸出描述", testCases, date,
                    0, 0, 0, "",
                    new String[]{"if (bmi < 50)"},
                    new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>());
            problemRepository.save(problem);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Test
    @Transactional
    public void save() {
        List<HistoryCode> historyCodes = new ArrayList<>();
        Judge judge = new Judge(problem, student, 5, historyCodes);
        judgeRepository.save(judge);
    }

    @Override
    @Test
    @Transactional
    public void find() {
        List<HistoryCode> historyCodes = new ArrayList<>();
        Judge expected = new Judge(problem, student, 5, historyCodes);
        Judge result = judgeRepository.save(expected);
        assertEquals(expected, result);
    }

    @Override
    @Test
    @Transactional
    public void delete() {
        List<HistoryCode> historyCodes = new ArrayList<>();
        Judge expected = new Judge(problem, student, 5, historyCodes);
        judgeRepository.save(expected);
        judgeRepository.deleteById(expected.getId());
    }
}
