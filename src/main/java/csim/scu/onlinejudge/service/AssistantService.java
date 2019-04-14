package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.assistant.Assistant;
import csim.scu.onlinejudge.service.base.BaseService;

public interface AssistantService extends BaseService<Assistant, Long> {

    boolean existByAccount(String account);

    Assistant findByAccount(String account) throws EntityNotFoundException;

    int updatePasswordByAccount(String account, String oriPassword, String newPassword);
}
