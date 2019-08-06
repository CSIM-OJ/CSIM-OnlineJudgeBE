package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.team.Team;
import csim.scu.onlinejudge.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface TeamService extends BaseService<Team, Long> {

    public void createTeam(String problemId, List<Map<String, String>> pairs) throws EntityNotFoundException;

    public boolean existByAccount(String account);

    public Team findByAccount(String account) throws EntityNotFoundException;

    public List<Team> findByProblem(Problem problem);

    public Team findByProblemAndAccount(Problem problem, String account) throws EntityNotFoundException;

    public boolean existsByProblemAndAccount(Problem problem, String account);
}
