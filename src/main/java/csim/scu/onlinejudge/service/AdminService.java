package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.admin.Admin;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface AdminService extends BaseService<Admin, Long> {

    boolean existByAccount(String account);

    Admin findByAccount(String account) throws EntityNotFoundException;

    int updatePasswordByAccount(String account, String oriPassword, String newPassword);

}
