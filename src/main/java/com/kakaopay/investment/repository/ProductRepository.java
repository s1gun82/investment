package com.kakaopay.investment.repository;

import com.kakaopay.investment.vo.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository< Product, Integer > {

}
