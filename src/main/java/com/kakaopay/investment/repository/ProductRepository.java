package com.kakaopay.investment.repository;


import com.kakaopay.investment.vo.ProductVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<ProductVO, Integer > {

    @Query("SELECT p " +
            "FROM ProductVO p " +
            "WHERE p.started_at <?1 and p.finished_at >?1 ")
    List<ProductVO> findAllByCurrentDate(Date currentDateTime);


}
