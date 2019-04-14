package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.dao.domain.team.Team;
import csim.scu.onlinejudge.dao.repository.TeacherRepository;
import csim.scu.onlinejudge.dao.repository.TeamRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.TeamService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends BaseServiceImpl<Team, Long> implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public BaseRepository<Team, Long> getBaseRepository() {
        return teamRepository;
    }
}
