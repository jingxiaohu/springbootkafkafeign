package com.xing;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProducerApplicationTests {
    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void sendMsg() throws Exception {

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("id","1");
        param.add("name","jingxiaohu");
        String responseBody = this.mockMvc.perform(
                post("/send")
//                        .header("Authorization",token)
//                        .content(objectMapper.writeValueAsBytes(param))
                        .params(param)
                        .contentType(MediaType.APPLICATION_JSON)    //请求数据的格式
                        .accept(MediaType.APPLICATION_JSON_UTF8)         //接收返回数据的格式
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("responseBody==="+ responseBody);
    }
}
