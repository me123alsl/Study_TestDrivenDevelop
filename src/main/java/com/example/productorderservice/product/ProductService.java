package com.example.productorderservice.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public
class ProductService {
    private final ProductPort productPort;

    ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity addProduct(@RequestBody AddProductRequest request) {
        Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable final long productId) {
        Product product = productPort.getProduct(productId);

        final GetProductResponse response = new GetProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPolicy()
        );

        return ResponseEntity.ok(response);
    }

    @Transactional
    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable final Long productId,
                              @RequestBody final UpdateProductRequest request) {
        Product product = productPort.getProduct(productId);
        product.update(request.name(), request.price(), request.discountPolicy());

        productPort.save(product);
        return ResponseEntity.ok().build();
    }
}
