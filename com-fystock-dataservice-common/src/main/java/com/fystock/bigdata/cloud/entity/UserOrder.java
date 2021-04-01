package com.fystock.bigdata.cloud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserOrder implements Serializable {
    private static final long serialVersionUID = 8655851615465363473L;
    private String orderID;
    private String userID;
    private String number;
    private Double price;
    private String productID;
}
