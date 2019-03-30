package csim.scu.onlinejudge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

public class CommonApi extends OnlineJudgeApplicationTests {

    private String baseUrl = "/api";
    private Map<String, Object> map = new HashMap<>();

    @Test
    public void testLogin() throws Exception {
        String url = baseUrl + "/login";
        Map<String, Object> map = new HashMap<>();
        // student
//        map.put("account", "04156211");
//        map.put("password", "0000");
        // admin
//        map.put("account", "999999");
//        map.put("password", "0000");
        // assistant
//        map.put("account", "111111");
//        map.put("password", "0000");
        // teacher
        map.put("account", "666666");
        map.put("password", "0000");

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(map))
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        map = convertToMap(result.getResponse().getContentAsString());
        String status = map.get("status").toString();
        System.out.println(result.getResponse().getContentAsString());
        Assert.assertEquals("登入失敗","200", status);
    }
}
