package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.assistant.Assistant;
import csim.scu.onlinejudge.dao.repository.AssistantRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.AssistantService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistantServiceImpl extends BaseServiceImpl<Assistant, Long> implements AssistantService {

    private AssistantRepository assistantRepository;

    @Autowired
    public AssistantServiceImpl(AssistantRepository assistantRepository) {
        this.assistantRepository = assistantRepository;
    }

    @Override
    public BaseRepository<Assistant, Long> getBaseRepository() {
        return assistantRepository;
    }

    @Override
    public boolean existByAccount(String account) {
        return assistantRepository.existsByAccount(account);
    }

    @Override
    public Assistant findByAccount(String account) throws EntityNotFoundException {
        return assistantRepository.findByAccount(account).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public int updatePasswordByAccount(String account, String oriPassword, String newPassword) {
        return assistantRepository.updatePasswordByAccountAndPassword(account, oriPassword, newPassword);
    }
}
