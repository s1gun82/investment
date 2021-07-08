package com.kakaopay.investment.service;

import com.kakaopay.investment.repository.ProductRepository;

import com.kakaopay.investment.vo.ProductVO;
import com.kakaopay.investment.vo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseData getAllList() {

        //SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );

        Date todayDay = new java.util.Date();
        log.info("currentDateTime : " + todayDay );

        ResponseData response = new ResponseData();
        //List<ProductVO> productList = (List<ProductVO>)productRepository.findAll();
        List<ProductVO> productList = (List<ProductVO>)productRepository.findAllByCurrentDate(todayDay);

        response.put("result", productList);

        return response;
    }
}
