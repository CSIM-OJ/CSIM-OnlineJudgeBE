package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.team.Team;
import csim.scu.onlinejudge.dao.repository.ProblemRepository;
import csim.scu.onlinejudge.dao.repository.TeamRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.TeamService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

@Service
public class TeamServiceImpl extends BaseServiceImpl<Team, Long> implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public BaseRepository<Team, Long> getBaseRepository() {
        return teamRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createTeam(String problemId, List<Map<String, String>> pairs) throws EntityNotFoundException {
        List<Team> teams = new ArrayList<>();
        Problem problem = problemRepository.findById(Long.parseLong(problemId)).orElseThrow(EntityNotFoundException::new);
        Set<String> correctAccounts = new HashSet<>();
        for (Map<String, String> pair : pairs) {
            String correctAccount = pair.get("correctAccount");
            if (!correctAccounts.contains(correctAccount)) {
                Team team = new Team(problem, correctAccount, new ArrayList<>(), new ArrayList<>(), null);
                teams.add(team);
                correctAccounts.add(correctAccount);
            }
        }
        pairs.forEach(pair -> {
            String correctAccount = pair.get("correctAccount");
            String correctedAccount = pair.get("correctedAccount");
            teams.forEach(team -> {
                if (correctAccount.equals(team.getAccount())) {
                    List<String> correctedAccountList = team.getCorrectedAccount();
                    correctedAccountList.add(correctedAccount);
                    team.setCorrectedAccount(correctedAccountList);
                }
            });
        });
        saveAll(teams);
    }


    @Override
    public boolean existByAccount(String account) {
        return teamRepository.existsByAccount(account);
    }

    @Override
    public Team findByAccount(String account) throws EntityNotFoundException {
        return teamRepository.findByAccount(account).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Team> findByProblem(Problem problem) {
        return teamRepository.findByProblem(problem);
    }

    @Override
    public Team findByProblemAndAccount(Problem problem, String account) throws EntityNotFoundException {
        return teamRepository.findByProblemAndAccount(problem, account).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean existsByProblemAndAccount(Problem problem, String account) {
        return teamRepository.existsByProblemAndAccount(problem, account);
    }

}
