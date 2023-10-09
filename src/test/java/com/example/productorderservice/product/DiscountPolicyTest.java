package com.example.productorderservice.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscountPolicyTest {

    @Test
    void applyDiscount_none() {
        int price = 1000;
        final int discountedPrice = DiscountPolicy.NONE.applyDiscount(price);
        assertThat(discountedPrice).isEqualTo(price);
    }

    @Test
    void applyDiscount_fixed_1000() {
        int price = 2000;
        final int discountedPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price);
        assertThat(discountedPrice).isEqualTo(price - 1000);
    }

    @Test
    void applyDiscount_fixed_1000_under1000() {
        int price = 500;
        final int discountedPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price);
        assertThat(discountedPrice).isEqualTo(0);
    }
}