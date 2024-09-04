package com.ecommerce.KiranaKart.controller;

import com.ecommerce.KiranaKart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/createOrder")
    public String createOrder(@RequestParam double amount) {
        try {
            return paymentService.createOrder(amount);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while creating order: " + e.getMessage();
        }
    }
}
