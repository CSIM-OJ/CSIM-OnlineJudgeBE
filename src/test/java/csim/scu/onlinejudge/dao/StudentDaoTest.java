package csim.scu.onlinejudge.dao;

import csim.scu.onlinejudge.OnlineJudgeApplicationTests;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.dao.repository.StudentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentDaoTest extends OnlineJudgeApplicationTests implements BaseDao {

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    @Test
    public void save() {
        Student expected = new Student("04156199", "0000",
                "Jack", "104資管B", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        Student result = studentRepository.save(expected);
        assertEquals(expected, result);
    }

    @Override
    @Transactional
    @Test
    public void find() {
        Student expected = new Student("04156199", "0000",
                "Jack", "104資管B", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        studentRepository.save(expected);
        Student result = studentRepository.findById(expected.getId()).get();
        assertEquals(expected, result);
    }

    @Override
    @Transactional
    @Test
    public void delete() {
        Student student = new Student("04156199", "0000",
                "Jack", "104資管B", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        studentRepository.save(student);
        studentRepository.deleteById(student.getId());
    }
}
