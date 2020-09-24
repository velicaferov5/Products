package com.products.api.controller;

import com.products.api.model.Product;
import com.products.api.model.Stock;
import com.products.api.service.ProductService;
import com.products.api.service.StockService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * REST-ful services to get, add, remove and manage stock.
 */
@RestController
@RequestMapping(value = "/api/stock")
public class StockController {

    private ProductService productService;
    private StockService stockService;

    public StockController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    /**
     * Gets and returns stock amount
     *
     * @param id
     * @return stock amount
     */
    @GetMapping(value = "/get/{id}")
    public int getStockAmount (@PathVariable(name="id") int id) {
        return stockService.getStockAmountById(id);
    }

    /**
     * Adds {@param amount} stocks with product {@param id}
     *
     * @param id
     * @param amount
     * @return updated stock
     */
    @PostMapping(value = "/in/{id}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Stock> addStock (@PathVariable(name="id") int id, @PathVariable(name="amount") int amount) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return stockService.addStock(id, amount);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Takes out {@param amount} stocks with product {@param id}
     *
     * @param id
     * @param amount
     * @return optional updated stock
     */
    @PostMapping(value = "/out/{id}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Stock> takeStock (@PathVariable(name="id") int id, @PathVariable(name="amount") int amount) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return stockService.takeStock(id, amount);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Updates and sets {@param stock}
     *
     * @param stock
     * @return optional updated stock
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Stock> updateStock (@Valid @RequestBody Stock stock) {
        Optional<Product> product = productService.getProductById(stock.getId());
        if (product.isPresent()) {
            return stockService.updateStock(stock);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Removes stock
     *
     * @param id
     * @return result of remove
     */
    @DeleteMapping(value = "/remove/{id}")
    public boolean removeStock (@PathVariable(name="id") int id) {
        return stockService.removeStockById(id);
    }
}
