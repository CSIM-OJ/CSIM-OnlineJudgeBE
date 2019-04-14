package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.team.Team;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends BaseRepository<Team, Long> {

}
