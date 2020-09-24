package com.products.api.controller;

import com.products.api.model.Product;
import com.products.api.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ManageService manageService;

    public ProductController(ManageService manageService) {
        this.manageService = manageService;
    }

    /**
     * Adds new product to DB.
     *
     * @param product
     * @return product
     */
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct (@Valid @RequestBody Product product) {
        return manageService.addOrUpdateProduct(product);
    }

    /**
     * Gets product
     *
     * @param id
     * @return product
     */
    @GetMapping(value = "/get/{id}")
    public Product getProduct (@PathVariable(name="id") Integer id) {
        return manageService.getProductById(id);
    }

    /**
     * Removes product
     *
     * @param id
     */
    @DeleteMapping(value = "/remove/{id}")
    public void removeProduct (@PathVariable(name="id") Integer id) {
        manageService.removeProductById(id);
    }
}
