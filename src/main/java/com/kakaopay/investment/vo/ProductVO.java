package com.kakaopay.investment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakaopay.investment.type.ProductStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@NoArgsConstructor
@Data
@Entity
@Table(name="PRODUCT")
public class ProductVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Integer product_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long total_investing_amount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Date started_at;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Date finished_at;

    @Column(nullable = false)
    private Long investing_amt;

    @Column(nullable = false)
    private int investor_cnt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus product_status;



}
