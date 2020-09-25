package com.products.api.service;

import com.products.api.interfaces.ProductRepository;
import com.products.api.interfaces.StockRepository;
import com.products.api.model.Product;
import com.products.api.model.Stock;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    void getStockAmountById() {
        Stock stock = newStock();
        when(stockRepository.findById(anyInt())).thenReturn(Optional.of(stock));
        int actual = stockService.getStockAmountById(0);
        assertEquals(stock.getAmount(), actual);
    }

    @Test
    void addStock() {
        Stock stock = newStock();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(newProduct()));
        when(stockRepository.save(any())).thenReturn(stock);
        Stock actual = stockService.addStock(0, 0).get();
        assertStock(stock, actual);
    }

    @Test
    void takeStock() {
        Stock stock = newStock();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(newProduct()));
        when(stockRepository.save(any())).thenReturn(stock);
        Stock actual = stockService.takeStock(0, 0).get();
        assertStock(stock, actual);
    }

    @Test
    void updateStock() {
        Stock stock = newStock();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(newProduct()));
        when(stockRepository.save(any())).thenReturn(stock);
        Stock actual = stockService.updateStock(stock).get();
        assertStock(stock, actual);
    }

    @Test
    void removeStockById() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(newProduct()));
        assertTrue(stockService.removeStockById(0));
    }

    private Stock newStock() {
        Stock stock = new Stock();
        stock.setAmount(1);
        return stock;
    }

    Product newProduct() {
        Product product = new Product();
        product.setType("book");
        product.setName("Reality");
        product.setDescription("Book about Reality");
        return product;
    }

    private void assertStock(Stock expected, Stock actual) {
        assertTrue((expected == null && actual == null) || (expected != null && actual != null) );
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAmount(), actual.getAmount());
    }
}