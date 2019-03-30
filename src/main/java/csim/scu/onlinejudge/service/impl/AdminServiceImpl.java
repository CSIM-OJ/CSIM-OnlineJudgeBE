package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.AdminNotFoundException;
import csim.scu.onlinejudge.dao.domain.admin.Admin;
import csim.scu.onlinejudge.dao.repository.AdminRepository;
import csim.scu.onlinejudge.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public boolean existByAccount(String account) {
        return adminRepository.existsByAccount(account);
    }

    @Override
    public Admin findByAccount(String account) throws AdminNotFoundException {
        return adminRepository.findByAccount(account).orElseThrow(AdminNotFoundException::new);
    }

    @Override
    public int updatePasswordByAccount(String account, String oriPassword, String newPassword) {
        return adminRepository.updatePasswordByAccountAndPassword(account, oriPassword, newPassword);
    }
}
