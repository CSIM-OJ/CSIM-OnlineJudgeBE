package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.problembank.ProblemBank;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemBankRepository extends BaseRepository<ProblemBank, Long> {

}
