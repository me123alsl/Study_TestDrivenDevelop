package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class PaymentService {
    private PaymentPort paymentPort;

    public PaymentService(PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }


    @PostMapping("payments")
    public void payment(@RequestBody PaymentRequest request) {
        Order order = paymentPort.getOrder(request.orderId());
        final Payment payment = new Payment(order, request.cardNumber());

        paymentPort.pay(payment.getPrice(), payment.getCardNumber());
        paymentPort.save(payment);
    }
}
