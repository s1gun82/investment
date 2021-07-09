package com.kakaopay.investment.service;

import com.kakaopay.investment.vo.ResponseData;

import java.util.List;

public interface InvestmentService {

    ResponseData getAllProductList();

    ResponseData getMyOrderList(Long userId);

    ResponseData placeOrder(Long userId, Integer product_id, long investment_amt);
}
