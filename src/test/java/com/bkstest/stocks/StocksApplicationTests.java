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

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    public void testIexCloud() throws Exception {

        List<CompanyStocks> stocks = new ArrayList<>();
        stocks.add(new CompanyStocks("AAPL", 50));
        stocks.add(new CompanyStocks("HOG", 10));
        stocks.add(new CompanyStocks("MDSO", 1));
        stocks.add(new CompanyStocks("IDRA", 1));
        stocks.add(new CompanyStocks("MRSN", 1));

        String content = new ObjectMapper().writeValueAsString(new Asset(stocks));

        mockMvc.perform(post("/portfolio").content(content).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.value", greaterThan(1d))).andExpect(jsonPath("$.allocations", hasSize(greaterThan(1))));
    }
}
