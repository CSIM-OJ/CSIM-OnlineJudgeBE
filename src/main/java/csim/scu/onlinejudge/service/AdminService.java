package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.admin.Admin;
import csim.scu.onlinejudge.service.base.BaseService;

public interface AdminService extends BaseService<Admin, Long> {

    boolean existByAccount(String account);

    Admin findByAccount(String account) throws EntityNotFoundException;

    int updatePasswordByAccount(String account, String oriPassword, String newPassword);
}
