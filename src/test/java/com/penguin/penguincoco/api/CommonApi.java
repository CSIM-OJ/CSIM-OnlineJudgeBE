package com.penguin.penguincoco.api;

import com.penguin.penguincoco.penguinCocoApplicationTests;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class CommonApi extends penguinCocoApplicationTests {

    private String baseUrl = "/api";
    private Map<String, Object> para = new HashMap<>();


    @Test
    public void testLogin() throws Exception {
        String url = baseUrl + "/login";
        // student
        para.put("account", "04156211");
        para.put("password", "0000");
        // admin
//        map.put("account", "999999");
//        map.put("password", "0000");
        // assistant
//        map.put("account", "111111");
//        map.put("password", "0000");
        // teacher
//        map.put("account", "666666");
//        map.put("password", "0000");

        MvcResult response = mvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(para))
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        session = response.getRequest().getSession();

        para = convertToMap(response.getResponse().getContentAsString());
        String status = para.get("status").toString();
        assertEquals("登入失敗","200", status);
        System.out.println(response.getResponse().getContentAsString());
        assertEquals("account不對", "04156211", session.getAttribute("logged_in"));
        assertEquals("使用者權限不對", "student", session.getAttribute("user_type"));
    }

}
