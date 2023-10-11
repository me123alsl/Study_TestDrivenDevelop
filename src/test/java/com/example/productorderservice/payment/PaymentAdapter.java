package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import org.springframework.stereotype.Component;

@Component
class PaymentAdapter implements PaymentPort {

    final private PaymentGateway paymentGateway;
    final private PaymentRepository paymentRepository;

    PaymentAdapter(final PaymentGateway paymentGateway, final PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Order getOrder(final Long orderId) {
        return new Order(new Product("상품", 1000, DiscountPolicy.NONE), 1);
    }

    @Override
    public void pay(final int totalPrice, final String cardNumber) {
        paymentGateway.execute(totalPrice, cardNumber);

    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
