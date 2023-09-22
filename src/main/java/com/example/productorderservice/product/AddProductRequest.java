package com.example.productorderservice.product;

import org.springframework.util.Assert;

record AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {

  AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
    Assert.hasText(name, "상품명은 필수입니다.");
    Assert.isTrue(price > 0, "상품 가격은 0원보다 커야합니다.");
    Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
    this.name = name;
    this.price = price;
    this.discountPolicy = discountPolicy;
  }
}
