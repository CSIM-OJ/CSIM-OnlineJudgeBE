package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.AdminNotFoundException;
import csim.scu.onlinejudge.dao.domain.admin.Admin;

import java.util.Optional;

public interface AdminService {

    boolean existByAccount(String account);
    Admin findByAccount(String account) throws AdminNotFoundException;
    int updatePasswordByAccount(String account, String oriPassword, String newPassword);
}
