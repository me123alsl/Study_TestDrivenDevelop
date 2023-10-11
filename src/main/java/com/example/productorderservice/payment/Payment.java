package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;

class Payment {
    private Long id;
    private Order order;
    private String cardNumber;

    public Payment(final Order order, final String cardNumber) {
        this.order = order;
        this.cardNumber = cardNumber;
    }

    public void assignId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public int getPrice() {
        return order.getTotalPrice();
    }
}
