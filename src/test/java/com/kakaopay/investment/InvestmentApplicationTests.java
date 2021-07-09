package com.kakaopay.investment;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class InvestmentApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("전체 투자 상품 조회 테스트")
    public void getAllInvestmentList() throws Exception {

        MvcResult result = mvc.perform(get("/allProductList")
                                    .contentType("application/json")
                                    .header("Origin", "http://www.example.com"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String r = result.getResponse().toString();
        log.info("result:{}", r);
    }

    @Test
    @DisplayName("투자하기")
    public void placeOrder() throws Exception {

        MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
        reqParams.add("product_id", "2");
        reqParams.add("investment_amt", "100000");

        MvcResult result = mvc.perform(post("/order")
                .contentType("application/json")
                .params(reqParams)
                .header("X-USER-ID", "1111111"))
                .andExpect(status().isOk())
                .andReturn();

        String r = result.getResponse().toString();
        log.info("result:{}", r);
    }

    @Test
    @DisplayName("내가 투자한 목록 조회 테스트")
    public void getMyOrderList() throws Exception {

        MvcResult result = mvc.perform(get("/myOrderList")
                .contentType("application/json")
                .header("Origin", "http://www.example.com"))
                .andExpect(status().isOk())
                .andReturn();

        String r = result.getResponse().toString();
        log.info("result:{}", r);
    }


}
