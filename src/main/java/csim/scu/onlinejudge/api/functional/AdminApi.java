package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.api.base.BaseMethod;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "AdminApi", description = "管理員的相關Api")
@RequestMapping("/api/admin")
@RestController
public class AdminApi {

    private StudentService studentService;

    @Autowired
    public AdminApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "將大量學生加入評測系統",
            notes = "取得account、name、studentClass，並加入系統")
    @PostMapping(value = "/addStudentList")
    private Message addStudentList(@RequestBody Map<String, Object> map) {
        Message message;
        List<Map<String, String>> studentDatas = (List<Map<String, String>>) map.get("studentDatas");
        studentService.saveAllStudent(studentDatas);
        message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        return message;
    }
}