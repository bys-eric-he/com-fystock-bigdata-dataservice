package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}