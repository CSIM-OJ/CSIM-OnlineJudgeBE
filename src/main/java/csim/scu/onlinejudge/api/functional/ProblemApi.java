package csim.scu.onlinejudge.api.functional;


import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.exception.CourseNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.common.exception.ProblemNotFoundException;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.problem.ProblemInfo;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.service.CommonService;
import csim.scu.onlinejudge.service.ProblemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "ProblemApi", description = "題目的相關Api")
@RequestMapping("/api/problem")
@RestController
public class ProblemApi extends BaseApi {

    @ApiOperation(value = "取得題目資訊",
            notes = "取得題目資訊")
    @GetMapping(value = "/getInfo")
    private Message getInfo(String problemId) {
        Message message;
        try {
            ProblemInfo problemInfo = problemService.getInfo(Long.parseLong(problemId));
            message = new Message(ApiMessageCode.SUCCESS_STATUS, problemInfo);
        } catch (ProblemNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.GET_PROBLEM_INFO_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "取得課程下所有題目資訊",
            notes = "取得courseId，來獲取課程下所有題目資訊")
    @GetMapping(value = "/getProblems")
    private Message getProblems(String courseId) {
        Message message;

        try {
            List<Problem> problems = commonService.findByCourseId(Long.parseLong(courseId));
            message = new Message(ApiMessageCode.SUCCESS_STATUS, problems);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.GET_PROBLEMS_INFO_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "在課程中建立題目",
            notes = "取得題目資訊，並建立題目")
    @PostMapping(value = "/addProblem")
    private Message addProblem(@RequestBody Map<String, Object> map) throws ParseException {
        Message message;
        String courseId = map.get("courseId").toString();
        String name = map.get("name").toString();
        String type = map.get("type").toString();
        String category = map.get("category").toString();
        List<String> tagList = (List<String>) map.get("tag");
        String[] tag = tagList.toArray(new String[tagList.size()]);
        String description = map.get("description").toString();
        String inputDesc = map.get("inputDesc").toString();
        String outputDesc = map.get("outputDesc").toString();
        List<TestCase> testCases = (List<TestCase>) map.get("testCases");
        String deadlineStr = map.get("deadline").toString();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date deadline = df.parse(deadlineStr);
        try {
            commonService.createProblem(Long.parseLong(courseId), name, type, category,
                    tag, description, inputDesc,
                    outputDesc, testCases, deadline);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.ADD_PROBLEM_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "在課程中編輯題目",
            notes = "取得題目資訊，並更新題目")
    @PostMapping(value = "/editProblem")
    private Message editProblem(@RequestBody Map<String, Object> map) throws ParseException {
        Message message;
        String problemId = map.get("problemId").toString();
        String name = map.get("name").toString();
        String type = map.get("type").toString();
        String category = map.get("category").toString();
        List<String> tagList = (List<String>) map.get("tag");
        String[] tag = tagList.toArray(new String[tagList.size()]);
        String description = map.get("description").toString();
        String inputDesc = map.get("inputDesc").toString();
        String outputDesc = map.get("outputDesc").toString();
        List<TestCase> testCases = (List<TestCase>) map.get("testCases");
        String deadlineStr = map.get("deadline").toString();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date deadline = df.parse(deadlineStr);
        try {
            problemService.update(Long.parseLong(problemId), name,
                    type, category, tag, description,
                    inputDesc, outputDesc, testCases, deadline);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (ProblemNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.EDIT_PROBLEM_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "在課程中刪除題目",
            notes = "取得problemId，並刪除題目")
    @PostMapping(value = "/deleteProblem")
    private Message deleteProblem(@RequestBody Map<String, String> map) {
        Message message;
        String problemId = map.get("problemId");
        try {
            problemService.deleteById(Long.parseLong(problemId));
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.DELETE_PROBLEM_ERROR, "");
        }
        return message;
    }
}