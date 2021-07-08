package com.kakaopay.investment.service;

import com.kakaopay.investment.repository.ProductRepository;
import com.kakaopay.investment.vo.Product;
import com.kakaopay.investment.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseData getAllList() {

        ResponseData response = new ResponseData();
        List<Product> productList = (List<Product>)productRepository.findAll();

        response.put("result", productList);

        return response;
    }
}
