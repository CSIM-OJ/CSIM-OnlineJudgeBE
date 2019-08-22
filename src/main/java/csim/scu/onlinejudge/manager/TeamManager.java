package csim.scu.onlinejudge.manager;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.team.CommentResult;

import java.util.List;
import java.util.Map;

public interface TeamManager {

    public List<Map<String, String>> correctStuds(String problemId, String account) throws EntityNotFoundException;

    public Map<String, Boolean> checkCorrectStatus(String problemId, String account) throws EntityNotFoundException;

    public Map<String, Boolean> checkCorrectedStatus(String problemId, String account) throws EntityNotFoundException;

    public List<Map<String, Object>> correctInfo(String problemId, String account) throws EntityNotFoundException;

    public List<Map<String, Object>> correctedInfo(String problemId, String account) throws EntityNotFoundException;

    public void submitCorrect(String problemId, String account, List<Map<String, Object>> commentList) throws EntityNotFoundException;

    public List<Map<String, Object>> discussScore(String problemId) throws EntityNotFoundException;

    public List<Map<String, Object>> teacherCorrectInfo(String problemId) throws EntityNotFoundException;

    public void teacherSubmitCorrect(String teacherAccount, String problemId, List<Map<String, Object>> correctedList) throws EntityNotFoundException;
}
