package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.dao.domain.problembank.ProblemBank;
import csim.scu.onlinejudge.service.ProblemBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "ProblemBankApi", description = "題庫的相關Api")
@RequestMapping("/api/problemBank")
@RestController
public class ProblemBankApi {

    private ProblemBankService problemBankService;

    @Autowired
    public ProblemBankApi(ProblemBankService problemBankService) {
        this.problemBankService = problemBankService;
    }

    @ApiOperation(value = "在題庫中建立題目",
            notes = "取得題目資訊，並建立題目")
    @PostMapping(value = "/addProblem")
    private Message addProblem(@RequestBody Map<String, Object> map) {
        Message message;
        String name = map.get("name").toString();
        String type = map.get("type").toString();
        String category = map.get("category").toString();
        List<String> tagList = (List<String>) map.get("tag");
        String[] tag = tagList.toArray(new String[tagList.size()]);
        String description = map.get("description").toString();
        String inputDesc = map.get("inputDesc").toString();
        String outputDesc = map.get("outputDesc").toString();
        List<TestCase> testCases = (List<TestCase>) map.get("testCases");

        ProblemBank problemBank = new ProblemBank(name, type, category, tag, description, inputDesc, outputDesc, testCases);
        problemBankService.save(problemBank);
        message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        return message;
    }

    @ApiOperation(value = "在題庫中建立題目",
            notes = "取得題目資訊，並建立題目")
    @GetMapping(value = "/getAllProblem")
    private Message getAllProblem() {
        Message message;
        message = new Message(ApiMessageCode.SUCCESS_STATUS, problemBankService.findAll());
        return message;
    }

    @ApiOperation(value = "在題庫中建立題目",
            notes = "取得題目資訊，並建立題目")
    @PostMapping(value = "/deleteProblem")
    private Message deleteProblem(@RequestBody Map<String, String> map) {
        Message message;
        Long problemBankId = Long.parseLong(map.get("problemBankId"));
        try {
            problemBankService.delete(problemBankId);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.DELETE_PROBLEMBANK_ERROR, "");
        }
        return message;
    }
}
