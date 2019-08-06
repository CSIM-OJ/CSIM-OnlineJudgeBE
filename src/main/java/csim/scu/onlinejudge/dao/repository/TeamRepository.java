package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.team.Team;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends BaseRepository<Team, Long> {

    boolean existsByAccount(String account);
    Optional<Team> findByAccount(String account);
    List<Team> findByProblem(Problem problem);
    Optional<Team> findByProblemAndAccount(Problem problem, String account);
    boolean existsByProblemAndAccount(Problem problem, String account);
}
