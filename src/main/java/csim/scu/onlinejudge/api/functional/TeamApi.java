package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.service.TeamService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "TeamApi", description = "分組的相關Api")
@RequestMapping("/api/team")
@RestController
public class TeamApi {

    private TeamService teamService;

    @Autowired
    public TeamApi(TeamService teamService) {
        this.teamService = teamService;
    }
}
