package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.api.base.BaseMethod;
import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.service.AssistantService;
import csim.scu.onlinejudge.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "AssistantApi", description = "助教的相關Api")
@RequestMapping("/api/assistant")
@RestController
public class AssistantApi extends BaseApi {

    @ApiOperation(value = "將學生列表加入課程",
            notes = "取得courseId、accountList，並加入課程")
    @PostMapping(value = "/addStudentList")
    private Message addStudentList(@RequestBody Map<String, Object> map) {
        return BaseMethod.addStudentList(map, commonService);
    }

    @ApiOperation(value = "將學生列表退出課程",
            notes = "取得courseId、accountList，並退出課程")
    @PostMapping(value = "/deleteStudentList")
    private Message deleteStudentList(@RequestBody Map<String, Object> map) {
        return BaseMethod.deleteStudentList(map, commonService);
    }
}
