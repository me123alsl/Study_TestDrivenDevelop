package com.example.productorderservice.product;

interface ProductPort {
    public void save(Product product);

    Product getProduct(long productId);
}
