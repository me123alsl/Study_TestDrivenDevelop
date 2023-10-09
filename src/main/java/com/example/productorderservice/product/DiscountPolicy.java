package com.example.productorderservice.product;

public enum DiscountPolicy {
    NONE {
        @Override
        int applyDiscount(final int price) {
            return price;
        }
    },
    FIX_1000_AMOUNT {
        @Override
        int applyDiscount(final int price) {
            return price - 1000 < 0 ? 0: price - 1000;
        }
    };

    abstract int applyDiscount(final int price);
}
