package com.kakaopay.investment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
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
@IdClass( value = User.PK.class )
public class User {

    @Id
    private Long user_id;

    @Id
    @ManyToOne
    @JoinColumn(name ="PRODUCT_ID")
    private ProductVO product;

    @Column(nullable = false)
    private Long investment_amount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Date investment_date;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {

        private static final long serialVersionUID = 1452817308876549380L;

        private long user_id;
        private int product;
    }
}
