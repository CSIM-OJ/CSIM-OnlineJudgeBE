package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.exception.JudgeNotFoundException;
import csim.scu.onlinejudge.common.exception.ProblemNotFoundException;
import csim.scu.onlinejudge.common.exception.StudentNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.service.CommonService;
import csim.scu.onlinejudge.service.JudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(value = "JudgeApi", description = "批改的相關Api")
@RequestMapping("/api/judge")
@RestController
public class JudgeApi extends BaseApi {

    @ApiOperation(value = "批改代碼",
            notes = "取得ProblemId、code、language，並進行批改代碼")
    @PostMapping(value = "/judgeCode")
    private Message judgeCode(@RequestBody Map<String, String> map, HttpSession session) {
        Message message;

        String account = getUserAccount(session);
        String problemId = map.get("problemId");
        String code = map.get("code");
        String language = map.get("language");
        try {
            commonService.judgeCode(Long.parseLong(problemId), code, language, account);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (JudgeNotFoundException | ProblemNotFoundException | StudentNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.JUDGE_CODE_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "取得批改後的資訊",
            notes = "取得problemId、account，來獲取批改後的資訊")
    @GetMapping(value = "/judgedInfo")
    private Message getJudgedInfo(String problemId, HttpSession session) {
        Message message;

        String account = getUserAccount(session);
        try {
            Judge judge = commonService.findByProblemIdAndStudentAccount(Long.parseLong(problemId), account);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, judge);
        } catch (ProblemNotFoundException | StudentNotFoundException | JudgeNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.GET_JUDGED_INFO_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "檢查學生在此題是否已被批改",
            notes = "取得problemId、account，來進行檢查")
    @GetMapping(value = "/checkJudged")
    private Message checkJudged(String problemId, HttpSession session) {
        Message message;

        String account = getUserAccount(session);
        try {
            boolean isExist = commonService.existByProblemIdAndStudentAccount(Long.parseLong(problemId), account);
            Map<String, Boolean> map = new HashMap<>();
            map.put("judged", isExist);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, map);
        } catch (ProblemNotFoundException | StudentNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.CHECK_JUDGE_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "判斷此題的抄襲",
            notes = "取得problemId，並判斷此題的抄襲")
    @PostMapping(value = "/judgeCopy")
    private Message judgeCopy(@RequestBody Map<String, String> map) {
        Message message;
        String problemId = map.get("problemId");
        try {
            commonService.judgeCopy(Long.parseLong(problemId));
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (ProblemNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.JUDGE_COPY_ERROR, "");
        }
        return message;
    }
}
