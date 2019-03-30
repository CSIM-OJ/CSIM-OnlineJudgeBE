package csim.scu.onlinejudge.api.functional;

import csim.scu.onlinejudge.api.base.BaseApi;
import csim.scu.onlinejudge.common.exception.AdminNotFoundException;
import csim.scu.onlinejudge.common.exception.AssistantNotFoundException;
import csim.scu.onlinejudge.common.exception.StudentNotFoundException;
import csim.scu.onlinejudge.common.exception.TeacherNotFoundException;
import csim.scu.onlinejudge.common.message.ApiMessageCode;
import csim.scu.onlinejudge.common.message.Message;
import csim.scu.onlinejudge.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(value = "CommonApi", description = "登入、登出、檢查登入狀態")
@RequestMapping("/api")
@RestController
public class CommonApi extends BaseApi {

    @ApiOperation(value = "使用者登入",
            notes = "回傳何種身分:student、teacher、assistant、admin")
    @PostMapping(value = "/login")
    private Message login(@RequestBody Map<String, String> map,
                          HttpSession session) {
        Message message;
        String account = map.get("account");
        String password = map.get("password");

        // 回傳使用者的身分
        String authority = null;
        try {
            authority = commonService.findUserAuthority(account, password);
            // 不是空值則登入成功
            if (!authority.equals("")) {
                setUserAccount(account, session);
                setUserType(authority, session);
                message = new Message(ApiMessageCode.SUCCESS_STATUS, authority);
            }
            else {
                message = new Message(ApiMessageCode.LOGIN_ERROR, authority);
            }
        } catch (StudentNotFoundException | TeacherNotFoundException | AssistantNotFoundException | AdminNotFoundException e) {
            e.printStackTrace();
            message = new Message(ApiMessageCode.LOGIN_ERROR, authority);
        }
        return message;
    }

    @ApiOperation(value = "使用者登出",
            notes = "將使用者登出，清除session")
    @PostMapping(value = "/logout")
    private Message logout(HttpSession session) {
        Message message;
        if (isLogin(session)) {
            destroySession(session);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, "");
        }
        else {
            message = new Message(ApiMessageCode.LOGOUT_ERROR, "");
        }
        return message;
    }

    @ApiOperation(value = "檢查使用者登入狀態",
            notes = "檢查使用者的session是否還存在")
    @GetMapping(value = "/checkLogin")
    private Message checkLogin(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Message message;
        boolean status = false;
        String authority = "";
        if (isLogin(session)) {
            status = true;
            authority = getUserType(session);
            map.put("status", status);
            map.put("authority", authority);
            message = new Message(ApiMessageCode.SUCCESS_STATUS, map);
        }
        else {
            map.put("status", status);
            map.put("authority", authority);
            message = new Message(ApiMessageCode.CHECK_LOGIN_ERROR, map);
        }
        return message;
    }

}
