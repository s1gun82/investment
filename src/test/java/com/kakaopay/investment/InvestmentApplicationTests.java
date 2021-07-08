package com.kakaopay.investment;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class InvestmentApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    @DisplayName("category 모델 train 파일 리스트 조회 테스트")
    public void getAllInvestmentList() throws Exception {

        MvcResult result = mvc.perform(get("/allList")
                                    .contentType("application/json")
                                    .header("Origin", "http://www.example.com"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String r = result.getResponse().getContentAsString();
        log.info("result:{}", r);
    }


}
