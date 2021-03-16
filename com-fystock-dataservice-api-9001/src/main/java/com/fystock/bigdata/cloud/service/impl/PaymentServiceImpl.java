package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.service.PaymentService;
import com.fystock.bigdata.cloud.dao.PaymentDao;
import com.fystock.bigdata.cloud.entity.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
