package com.kakaopay.investment.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserOrderDTO {

    private Long user_id;

    private int product_id;

    private String title;

    private Long total_investing_amount;

    private Long investment_amt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date investment_date;

}
