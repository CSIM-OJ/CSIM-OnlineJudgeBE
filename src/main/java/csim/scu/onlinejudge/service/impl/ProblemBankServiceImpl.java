package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.dao.domain.problembank.ProblemBank;
import csim.scu.onlinejudge.dao.repository.ProblemBankRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.ProblemBankService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemBankServiceImpl extends BaseServiceImpl<ProblemBank, Long> implements ProblemBankService {

    private ProblemBankRepository problemBankRepository;

    @Autowired
    public ProblemBankServiceImpl(ProblemBankRepository problemBankRepository) {
        this.problemBankRepository = problemBankRepository;
    }

    @Override
    public BaseRepository<ProblemBank, Long> getBaseRepository() {
        return problemBankRepository;
    }
}
