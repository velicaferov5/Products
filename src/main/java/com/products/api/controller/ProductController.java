package com.products.api.controller;

import com.products.api.model.Product;
import com.products.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * REST-ful services to get, add, remove and manage products
 */
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets product
     *
     * @param id
     * @return optional product
     */
    @GetMapping(value = "/get/{id}")
    public Optional<Product> getProduct (@PathVariable(name="id") Integer id) {
        return productService.getProductById(id);
    }

    /**
     * Adds new product to DB.
     *
     * @param product
     * @return product
     */
    @PutMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct (@Valid @RequestBody Product product) {
        return productService.addOrUpdateProduct(product);
    }

    /**
     * Removes product
     *
     * @param id
     * @return result of remove
     */
    @DeleteMapping(value = "/remove/{id}")
    public boolean removeProduct (@PathVariable(name="id") Integer id) {
        return productService.removeProductById(id);
    }
}
