package com.kakaopay.investment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakaopay.investment.type.ProductStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private int product_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long total_investing_amount;

    @Column(nullable = false)
    private Long current_investing_amount;

    @Column
    private int investorCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Date started_at;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Date finished_at;



}
