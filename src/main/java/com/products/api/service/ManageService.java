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
public class ManageService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ProductRepository productRepository;

    public ManageService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*ProductRepository repository;

    public ManageService(ProductRepository repository) {
        this.repository = repository;
    }
    public Product insertProduct(Product product) {
        repository.insert(product);
        return product;
    }*/

    public Product getProductById(Integer id) {
         Optional<Product> result = productRepository.findById(id);
         return result.orElseGet(Product::new);
    }

    public Product addOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void removeProductById(int id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
