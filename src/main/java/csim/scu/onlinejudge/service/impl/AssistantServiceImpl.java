package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.AssistantNotFoundException;
import csim.scu.onlinejudge.dao.domain.assistant.Assistant;
import csim.scu.onlinejudge.dao.repository.AssistantRepository;
import csim.scu.onlinejudge.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AssistantServiceImpl implements AssistantService {

    private AssistantRepository assistantRepository;

    @Autowired
    public AssistantServiceImpl(AssistantRepository assistantRepository) {
        this.assistantRepository = assistantRepository;
    }

    @Override
    public boolean existByAccount(String account) {
        return assistantRepository.existsByAccount(account);
    }

    @Override
    public Assistant findByAccount(String account) throws AssistantNotFoundException {
        return assistantRepository.findByAccount(account).orElseThrow(AssistantNotFoundException::new);
    }

    @Transactional
    @Override
    public Assistant save(Assistant assistant) {
        return assistantRepository.save(assistant);
    }

    @Override
    public int updatePasswordByAccount(String account, String oriPassword, String newPassword) {
        return assistantRepository.updatePasswordByAccountAndPassword(account, oriPassword, newPassword);
    }
}
