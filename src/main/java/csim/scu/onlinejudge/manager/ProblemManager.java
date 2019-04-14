package csim.scu.onlinejudge.manager;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface ProblemManager {

    List<Map<String, String>> getStudentProblemInfo(Long courseId, String type, boolean isJudge, String account) throws EntityNotFoundException;
}
