package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.dao.domain.problembank.ProblemBank;
import csim.scu.onlinejudge.service.base.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProblemBankService extends BaseService<ProblemBank, Long> {

    void update(Long problemBankId, String name,
                String category, String[] tag, String description,
                String inputDesc, String outputDesc,
                List<TestCase> testCases) throws EntityNotFoundException;

    List<Map<String, Object>> getAllProblem();
}
