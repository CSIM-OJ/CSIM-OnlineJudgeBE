package csim.scu.onlinejudge.dao;

import csim.scu.onlinejudge.OnlineJudgeApplicationTests;
import csim.scu.onlinejudge.dao.domain.admin.Admin;
import csim.scu.onlinejudge.dao.repository.AdminRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

public class AdminDaoTest extends OnlineJudgeApplicationTests implements BaseDao {

    @Autowired
    AdminRepository adminRepository;


    @Override
    @Test
    @Transactional
    public void save() {
        Admin expected = new Admin("999999", "0000",
                "管理員");
        Admin result = adminRepository.save(expected);
        assertEquals(expected, result);
    }

    @Override
    @Test
    @Transactional
    public void find() {
        Admin expected = new Admin("999999", "0000",
                "管理員");
        adminRepository.save(expected);
        Admin result = adminRepository.findById(expected.getId()).get();
        assertEquals(expected, result);
    }

    @Override
    @Test
    @Transactional
    public void delete() {
        Admin expected = new Admin("999999", "0000",
                "管理員");
        adminRepository.save(expected);
        adminRepository.deleteById(expected.getId());
    }
}
