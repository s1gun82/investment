package com.kakaopay.investment.repository;


import com.kakaopay.investment.vo.ProductVO;
import com.kakaopay.investment.vo.UserOrderVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface UserOrderRepository extends PagingAndSortingRepository<UserOrderVO, Integer> {


    @Query("SELECT u " +
            "FROM UserOrderVO u " +
            "WHERE u.user_id =?1 ")
    List<UserOrderVO> findByUserId(Long userId);

    @Query("SELECT u " +
            "FROM UserOrderVO u " +
            "WHERE u.user_id =?1 and u.product.product_id =?2")
    UserOrderVO findByUserIdAndProductId(Long userId, int productId);
}
