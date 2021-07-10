package com.kakaopay.investment.repository;


import com.kakaopay.investment.dto.UserOrderDTO;
import com.kakaopay.investment.vo.UserOrderVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface UserOrderRepository extends PagingAndSortingRepository<UserOrderVO, Integer> {

    @Query("SELECT new com.kakaopay.investment.dto.UserOrderDTO ( "+
            " u.user_id, p.product_id, p.title, p.total_investing_amount, u.investment_amt, u.investment_date) " +
            "FROM UserOrderVO u join u.product p " +
            "WHERE u.user_id =?1 ")
    List<UserOrderDTO> findByMyOrderList(Long userId);

    @Query("SELECT u " +
            "FROM UserOrderVO u " +
            "WHERE u.user_id =?1 and u.product.product_id =?2")
    UserOrderVO findByUserIdAndProductId(Long userId, int productId);
}
