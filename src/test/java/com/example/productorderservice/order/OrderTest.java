package com.example.productorderservice.order;

import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @Test
    void getTotalPrice() {
        final Order order = new Order(new Product("상품", 1000, DiscountPolicy.NONE), 2);

        final int totalPrice = order.getTotalPrice();

        assertThat(totalPrice).isEqualTo(2000);
    }
}