package com.example.productorderservice.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductPort productPort;

    @Test
    void 상품수정() {
        productService.addProduct(ProductSteps.상품등록요청_생성());

        final Long productId = 1L;
        final UpdateProductRequest request = ProductSteps.상품수정요청_생성();

        productService.updateProduct(productId, request);

        ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
        GetProductResponse productResponse = response.getBody();
        Assertions.assertThat(productResponse.name()).isEqualTo("상품 수정");
        Assertions.assertThat(productResponse.price()).isEqualTo(2000);
    }

}
