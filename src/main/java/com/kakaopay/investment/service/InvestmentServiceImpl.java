package com.kakaopay.investment.service;

import com.kakaopay.investment.common.InvestmentException;

import com.kakaopay.investment.repository.ProductRepository;

import com.kakaopay.investment.repository.UserOrderRepository;
import com.kakaopay.investment.type.ErrorCodeType;

import com.kakaopay.investment.type.ProductStatus;
import com.kakaopay.investment.vo.ProductVO;
import com.kakaopay.investment.vo.ResponseData;
import com.kakaopay.investment.dto.UserOrderDTO;
import com.kakaopay.investment.vo.UserOrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;


    @Override
    public ResponseData getAllProductList() {

        ResponseData response = new ResponseData();

        response.put("Product List", getValidProductList());

        return response;
    }

    @Override
    public ResponseData getMyOrderList(Long userId) {

        ResponseData response = new ResponseData();
        List<UserOrderDTO> userOrderList = userOrderRepository.findByMyOrderList(userId);

        response.put("MyOrderList", userOrderList);
        return response;
    }

    @Transactional
    @Override
    public ResponseData placeOrder(Long userId, Integer product_id, long investment_amt) {

        ResponseData response = new ResponseData();

        //상품이 존재 하는지 체크
        ProductVO product = productRepository.findById(product_id).
                orElseThrow(() -> new InvestmentException(ErrorCodeType.PRODUCT_NOT_FOUND));

        try {
            //상품 유효성 체크
            productValidationCheck(product, investment_amt);
            //상품 판매 진행
            processOrder(product, userId, investment_amt);
            response.setResultText("투자 상품이 체결 되었습니다.");
        } catch (InvestmentException e) {
            response.setResultText(ErrorCodeType.fromValue(e.getErrorCode()).label);
        }
        return response;

    }

    private List<ProductVO> getValidProductList() {

        Date todayDay = new java.util.Date();
        log.info("currentDateTime : " + todayDay);

        return productRepository.findAllByCurrentDate(todayDay);
    }


    private void processOrder(ProductVO product, Long userId, Long investment_amt) {


        if(userOrderRepository.findByUserIdAndProductId(userId, product.getProduct_id()) != null) {
            throw new InvestmentException(ErrorCodeType.ORDER_TAKEN);
        }

        UserOrderVO userOrder = new UserOrderVO();
        userOrder.setUser_id(userId);
        userOrder.setInvestment_amt(investment_amt);
        userOrder.setProduct(product);
        userOrderRepository.save(userOrder);

        product.setInvestor_cnt(product.getInvestor_cnt() + 1);
        product.setInvesting_amt(product.getInvesting_amt() + investment_amt);

        productRepository.save(product);
    }

    private void productValidationCheck(ProductVO product, long investment_amt ) {

        //상품이 모집 완료 되었는지 체크
        if(product.getProduct_status().equals(ProductStatus.모집완료)) {
            throw new InvestmentException(ErrorCodeType.PRODUCT_SOLD_OUT);
        }

        Date todayDay = new java.util.Date();

        //상품 판매 기간이 아직 유효한지 체크
        if(product.getFinished_at().before(todayDay)) {
            throw new InvestmentException(ErrorCodeType.PRODUCT_NOT_FOUND);
        }

        // 상품의 투자 모집 금액이 현재 모집금액 + 나의 투자금액보자 작으면 SOLD_OUT 상태로 응답 한다.
        if (product.getTotal_investing_amount() < product.getInvesting_amt() + investment_amt) {
            throw new InvestmentException(ErrorCodeType.PRODUCT_SOLD_OUT);
        }

    }
}