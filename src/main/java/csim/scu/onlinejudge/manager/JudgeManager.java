package csim.scu.onlinejudge.manager;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.dao.domain.student.BestCodeRank;
import csim.scu.onlinejudge.dao.domain.student.CorrectRank;

import java.util.List;
import java.util.Map;

public interface JudgeManager {

    void judgeCode(Long problemId, String code, String language, String account) throws EntityNotFoundException;

    Judge findByProblemIdAndStudentAccount(Long problemId, String account) throws EntityNotFoundException;

    boolean existByProblemIdAndStudentAccount(Long problemId, String account) throws EntityNotFoundException;

    void judgeCopy(Long problemId) throws EntityNotFoundException;

    int updateJudgeRateByProblemIdAndAccount(double rate, Long problemId, String account) throws EntityNotFoundException;

    List<Map<String, Object>> getStudentHistoryScoreInfo(Long courseId, String account) throws EntityNotFoundException;

    List<CorrectRank> getCorrectRank(Long courseId) throws EntityNotFoundException;

    List<BestCodeRank> getBestCodeRank(Long courseId) throws EntityNotFoundException;

    List<Map<String, Object>> getProblems(Long courseId) throws EntityNotFoundException;

    Map<String, Object> getJudgeInfo(Long problemId, String account) throws EntityNotFoundException;
}
