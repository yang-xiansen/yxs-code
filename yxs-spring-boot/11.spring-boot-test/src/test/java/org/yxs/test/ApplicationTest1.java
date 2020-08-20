package org.yxs.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest1 {

    /**
     * 模拟mvc
     */
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


    @Before
    public void beforeTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * @author: yang-xiansen
     * @Date: 2020/08/19 20:27
     * @Description: MockMvc模拟MVC请求
     */
    @Test
    public void Test1() throws Exception {
        //模拟get请求
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}", "yxs"));
        //模拟post请求
        mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));
        //模拟文件上传
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/fileupload").file("file", "文件内容".getBytes("utf-8")));

        //模拟请求参数
        // 模拟发送一个message参数，值为hello
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("message", "hello"));
        // 模拟提交一个checkbox值，name为hobby，值为sleep和eat
        mockMvc.perform(MockMvcRequestBuilders.get("/saveHobby").param("hobby", "sleep", "eat"));
        //使用MultiValueMap构造参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name", "yxs");
        params.add("hobby", "sleep");
        params.add("hobby", "eat");
        mockMvc.perform(MockMvcRequestBuilders.get("/hobby/save").params(params));
        //模拟发送json参数
        String jsonStr = "{\"username\":\"Dopa\",\"passwd\":\"ac3af72d9f95161a502fd326865c2f15\",\"status\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));



    }





















}
