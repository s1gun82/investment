package com.kakaopay.investment.controller;

import com.kakaopay.investment.service.InvestmentService;
import com.kakaopay.investment.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @GetMapping(value = "/allProductList")
    public ResponseData getAllInvestmentList() {

        return investmentService.getAllProductList();
    }

    @GetMapping(value = "/myOrderList")
    public ResponseData getMyOrderList(@RequestHeader("X-USER-ID") Long userId) {

        return investmentService.getMyOrderList(userId);
    }

    @PostMapping(value = "/order")
    public ResponseData order(@RequestHeader("X-USER-ID") Long userId,
                              @RequestParam int product_id,
                              @RequestParam long investment_amt ) {

        return investmentService.placeOrder(userId, product_id,investment_amt);

    }
}
