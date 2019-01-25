package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class DemoControllerTest {

    private MockMvc mockMvc;



    @Before
    public void setUp() throws Exception {
        //mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();

    }

    @After
    public void tearDown() throws Exception {

    }

    List<String> list= Arrays.asList("ABC","DEF");

    @Test
    public void testSampleGetter() throws Exception {
        mockMvc.perform(get("/helloworld"))
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.)
                .andExpect(MockMvcResultMatchers.content().string("HelloWorld"));

    }

    @Test
    public void testSampleInnerGetter() throws Exception {
            list.stream().forEach(System.out::println);
            mockMvc.perform(get("/helloinnerclass").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title", Matchers.is("Title")))
                    .andExpect(jsonPath("$.value", Matchers.is("Value")));
            list.add("BAZ");
            list.stream().forEach(System.out::println);

    }
}