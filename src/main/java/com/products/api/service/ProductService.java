package com.products.api.service;

import com.products.api.interfaces.ProductRepository;
import com.products.api.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Services to manage product
 */
@Service
public class ProductService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Integer id) {
         return productRepository.findById(id);
    }

    public Product addOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean removeProductById(int id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
