package com.products.api.controller;

import com.products.api.model.Order;
import com.products.api.model.Product;
import com.products.api.model.Stock;
import com.products.api.service.OrderService;
import com.products.api.service.ProductService;
import com.products.api.service.StockService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * REST-ful services to make and manage order.
 */
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Gets and returns order
     *
     * @param id
     * @return order
     */
    @GetMapping(value = "/get/{id}")
    public Optional<Order> getOrder (@PathVariable(name="id") int id) {
        return orderService.getOrderById(id);
    }

    /**
     * Make new order
     *
     * @param products
     * @return optional order
     */
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Order> makeOrder (@Valid @RequestBody Map<Product, Integer> products) {
        return orderService.makeOrder(products);
    }

    /**
     * Cancels order and sets status to CANCELLED
     *
     * @param id
     * @return optional cancelled order
     */
    @PutMapping(value = "/cancel/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Order> cancelOrder (@PathVariable(name="id") int id) {
        return orderService.cancelOrder(id);
    }
}
