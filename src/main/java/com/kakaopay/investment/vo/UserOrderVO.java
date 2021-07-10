package com.kakaopay.investment.vo;


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
@Table(name="USER_ORDER")
public class UserOrderVO implements Serializable {

    @Id
    @Column(name ="USER_ID")
    private Long user_id;

    @ManyToOne(targetEntity=ProductVO.class, fetch = FetchType.EAGER)
    @JoinColumn(name ="PRODUCT_ID")
    private ProductVO product;

    @Column(nullable = false)
    private Long investment_amt;

    @Temporal( TemporalType.TIMESTAMP )
    @Column(nullable = false, updatable = false)
    private Date investment_date;

    @PrePersist
    protected void onPersist() {
        this.investment_date = new Date();
    }

}
