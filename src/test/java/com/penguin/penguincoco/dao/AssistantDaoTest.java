package com.penguin.penguincoco.dao;

import com.penguin.penguincoco.penguinCocoApplicationTests;
import com.penguin.penguincoco.dao.domain.assistant.Assistant;
import com.penguin.penguincoco.dao.repository.AssistantRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AssistantDaoTest extends penguinCocoApplicationTests implements BaseDao {

    @Autowired
    AssistantRepository assistantRepository;

    @Override
    @Test
    @Transactional
    public void save() {
        Assistant expected = new Assistant("111111", "0000", "助教", new ArrayList<>());
        Assistant result = assistantRepository.save(expected);
        assertEquals(expected, result);
    }

    @Override
    @Test
    @Transactional
    public void find() {
        Assistant expected = new Assistant("111111", "0000", "助教", new ArrayList<>());
        assistantRepository.save(expected);
        Assistant result = assistantRepository.findById(expected.getId()).get();
        assertEquals(expected, result);
    }

    @Override
    @Test
    @Transactional
    public void delete() {
        Assistant expected = new Assistant("111111", "0000", "助教", new ArrayList<>());
        assistantRepository.save(expected);
        assistantRepository.deleteById(expected.getId());
    }
}
