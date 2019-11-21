package com.bkstest.stocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StocksApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
//        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.content").value("Hello, World!"));
//    }
//
//    @Test
//    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
//        this.mockMvc.perform(get("/greeting").param("name", "Spring Community")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
//    }

//    @Test
//    public void testIfItWorks() throws Exception {
//
//        List<Stock> stocks = new ArrayList<>();
//        stocks.add(new Stock("AAPL", 50));
//        stocks.add(new Stock("HOG", 10));
//        stocks.add(new Stock("MDSO", 1));
//        stocks.add(new Stock("IDRA", 1));
//        stocks.add(new Stock("MRSN", 1));
//
//        String content = new ObjectMapper().writeValueAsString(new Asset(stocks));
//
//        mockMvc.perform(post("/portfolio").content(content).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
//    }
}
