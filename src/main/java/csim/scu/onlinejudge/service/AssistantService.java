package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.AssistantNotFoundException;
import csim.scu.onlinejudge.dao.domain.assistant.Assistant;

public interface AssistantService {

    boolean existByAccount(String account);
    Assistant findByAccount(String account) throws AssistantNotFoundException;
    Assistant save(Assistant assistant);
    int updatePasswordByAccount(String account, String oriPassword, String newPassword);
}
