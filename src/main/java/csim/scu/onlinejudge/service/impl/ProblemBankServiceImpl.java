package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.dao.domain.problembank.ProblemBank;
import csim.scu.onlinejudge.dao.repository.ProblemBankRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.ProblemBankService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void update(Long problemBankId, String name, String category, String[] tag, String description, String inputDesc, String outputDesc, List<TestCase> testCases) throws EntityNotFoundException {
        ProblemBank problemBank = findById(problemBankId);
        problemBank.setName(name);
        problemBank.setCategory(category);
        problemBank.setDescription(description);
        problemBank.setTag(tag);
        problemBank.setInputDesc(inputDesc);
        problemBank.setOutputDesc(outputDesc);
        problemBank.setTestCases(testCases);
        save(problemBank);
    }
}
