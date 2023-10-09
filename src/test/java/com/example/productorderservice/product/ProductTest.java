package com.example.productorderservice.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void update() {
        final Product product = new Product("상품명", 1000, DiscountPolicy.NONE);

        product.update("상품명 수정", 2000, DiscountPolicy.NONE);

        assertThat(product.getName()).isEqualTo("상품명 수정");
        assertThat(product.getPrice()).isEqualTo(2000);
    }

    @Test
    void none_discounted_product() {
        final Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
        final int discountedPrice = product.getDiscountedPrice();
        assertThat(discountedPrice).isEqualTo(product.getPrice());
    }

    @Test
    void none_fixed1000_product() {
        final Product product = new Product("상품명", 500, DiscountPolicy.FIX_1000_AMOUNT);
        final int discountedPrice = product.getDiscountedPrice();
        assertThat(discountedPrice).isEqualTo(product.getPrice() < 1000 ? 0 : product.getPrice() - 1000);
    }
}