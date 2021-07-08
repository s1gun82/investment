package com.kakaopay.investment.controller;

import com.kakaopay.investment.service.InvestmentService;
import com.kakaopay.investment.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @GetMapping(value = "/allList")
    public ResponseData getAllInvestmentList() {

        return investmentService.getAllList();
    }
}
