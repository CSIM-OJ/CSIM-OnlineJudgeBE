package com.penguin.penguincoco.dao;

import com.penguin.penguincoco.penguinCocoApplicationTests;
import com.penguin.penguincoco.dao.domain.teacher.Teacher;
import com.penguin.penguincoco.dao.repository.TeacherRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class TeacherDaoTest extends penguinCocoApplicationTests implements BaseDao {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    @Test
    @Transactional
    public void save() {
        Teacher expected = new Teacher("666666", "0000", "教授", new ArrayList<>());
        Teacher result = teacherRepository.save(expected);
        assertEquals(expected, result);
    }

    @Override
    @Test
    @Transactional
    public void find() {
        Teacher expected = new Teacher("666666", "0000", "教授", new ArrayList<>());
        teacherRepository.save(expected);
        Teacher result = teacherRepository.findById(expected.getId()).get();
        assertEquals(expected, result);
    }

    @Override
    @Test
    @Transactional
    public void delete() {
        Teacher expected = new Teacher("666666", "0000", "教授", new ArrayList<>());
        teacherRepository.save(expected);
        teacherRepository.deleteById(expected.getId());
    }

}
