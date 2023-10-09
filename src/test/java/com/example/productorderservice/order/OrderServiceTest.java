package com.example.productorderservice.order;

import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import com.example.productorderservice.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderPort orderPort;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        orderPort = new OrderPort() {
            @Override
            public Product getProductById(long productId) {
                return new Product("상품명", 1000, DiscountPolicy.NONE.NONE);
            }

            @Override
            public void save(Order order) {
                orderRepository.save(order);

            }
        };
        orderService = new OrderService();
    }

    @Test
    void 상품주문() {
        final long productId = 1L;
        final int quantity = 2;
        final CreateOrderRequest request = new CreateOrderRequest(productId, quantity);
        orderService.createOrder(request);
    }

    private record CreateOrderRequest(long productId, int quantity) {
        CreateOrderRequest {
            Assert.notNull(productId, "상품 ID는 필수입니다.");
            Assert.isTrue(quantity > 0, "상품 수량은 0보다 커야 합니다.");
        }
    }

    private class OrderService {
        public void createOrder(final CreateOrderRequest request) {
            final Product product = orderPort.getProductById(request.productId);
            final Order order = new Order(product, request.quantity);
            orderPort.save(order);
        }
    }

    private class OrderAdapter implements OrderPort {

        private final ProductRepository productRepository;
        private final OrderRepository orderRepository;

        private OrderAdapter(ProductRepository productRepository, OrderRepository orderRepository) {
            this.productRepository = productRepository;
            this.orderRepository = orderRepository;
        }

        public Product getProductById(long productId) {
            return productRepository.findById(productId).orElseGet(() -> {
                throw new IllegalArgumentException("상품이 존재하지 않습니다.");
            });
        }

        public void save(Order order) {
            orderRepository.save(order);
        }
    }

    private class Order {
        private Long id;
        private final Product product;
        private final int quantity;

        public Order(final Product product, final int quantity) {
            this.product = product;
            this.quantity = quantity;
            Assert.notNull(product, "상품은 필수입니다.");
            Assert.isTrue(quantity > 0, "상품 수량은 0보다 커야 합니다.");
        }

        public void assignId(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    private class OrderRepository {
        private final Map<Long, Order> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(Order order) {
            order.assignId(++sequence);
            persistence.put(order.getId(), order);
        }
    }

    private interface OrderPort {
        Product getProductById(long productId);

        void save(Order order);
    }
}
