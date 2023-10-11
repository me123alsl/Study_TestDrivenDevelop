package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService {

    private final OrderPort orderPort;

    public OrderService(OrderPort orderPort){
        this.orderPort = orderPort;
    }

    @PostMapping("/orders")
    @Transactional
    public ResponseEntity<Void> createOrder(@RequestBody final CreateOrderRequest request) {
        final Product product = orderPort.getProductById(request.productId());
        final Order order = new Order(product, request.quantity());
        orderPort.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
