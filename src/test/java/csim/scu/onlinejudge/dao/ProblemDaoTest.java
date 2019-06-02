package csim.scu.onlinejudge.dao;

import csim.scu.onlinejudge.OnlineJudgeApplicationTests;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.dao.domain.teacher.Teacher;
import csim.scu.onlinejudge.dao.repository.CourseRepository;
import csim.scu.onlinejudge.dao.repository.ProblemRepository;
import csim.scu.onlinejudge.dao.repository.TeacherRepository;
import org.junit.After;
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

public class ProblemDaoTest extends OnlineJudgeApplicationTests implements BaseDao {

    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    private Course course;

    @Before
    public void setUp() {
        Teacher teacher = new Teacher("666666", "0000", "教授", new ArrayList<>());
        teacherRepository.save(teacher);
        course = new Course(teacher, "計算機程式設計",
                "104上", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        courseRepository.save(course);
    }

    @Override
    @Test
    @Transactional
    public void save() {
        List<TestCase> testCases = Arrays.asList(
                new TestCase("123", "123"),
                new TestCase("456", "456"),
                new TestCase("789", "789")
        );
        String deadline = "2019-02-17";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(deadline);
            Problem expected = new Problem(course, "計算速率",
                    "作業", "輸入輸出",
                    new String[]{"Java","條件","迴圈"},
                    0,  "描述", "輸入描述",
                    "輸出描述", testCases, date,
                    0, 0, 0,
                    "",
                    new String[]{"if", "while", "for"},
                    new String[]{"if (bmi < 50)"},new ArrayList<>(), new ArrayList<>());
            Problem result = problemRepository.save(expected);
            assertEquals(expected, result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Test
    @Transactional
    public void find() {
        List<TestCase> testCases = Arrays.asList(
                new TestCase("123", "123"),
                new TestCase("456", "456"),
                new TestCase("789", "789")
        );
        String deadline = "2019-02-17";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(deadline);
            Problem expected = new Problem(course, "計算速率",
                    "作業", "輸入輸出",
                    new String[]{"Java","條件","迴圈"},
                    0,  "描述", "輸入描述",
                    "輸出描述", testCases, date,
                    0, 0, 0,
                    "",
                    new String[]{"if", "while", "for"},
                    new String[]{"if (bmi < 50)"},new ArrayList<>(), new ArrayList<>());
            problemRepository.save(expected);
            Problem result = problemRepository.findByName("計算速率").get();
            assertEquals(expected, result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Test
    @Transactional
    public void delete() {
        List<TestCase> testCases = Arrays.asList(
                new TestCase("123", "123"),
                new TestCase("456", "456"),
                new TestCase("789", "789")
        );
        String deadline = "2019-02-17";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(deadline);
            Problem expected = new Problem(course, "計算速率",
                    "作業", "輸入輸出",
                    new String[]{"Java","條件","迴圈"},
                    0,  "描述", "輸入描述",
                    "輸出描述", testCases, date,
                    0, 0, 0,
                    "",
                    new String[]{"if", "while", "for"},
                    new String[]{"if (bmi < 50)"},new ArrayList<>(), new ArrayList<>());
            problemRepository.save(expected);
            problemRepository.deleteById(expected.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
