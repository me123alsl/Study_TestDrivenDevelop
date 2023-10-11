package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;
    private String cardNumber;

    public Payment(final Order order, final String cardNumber) {
        this.order = order;
        this.cardNumber = cardNumber;
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
