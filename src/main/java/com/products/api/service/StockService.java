package com.products.api.service;

import com.products.api.interfaces.StockRepository;
import com.products.api.model.Stock;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Services to manage stock
 */
@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Gets stock amount by {@param id}.
     * @param id
     * @return stock's id
     */
    public int getStockAmountById(int id) {
         Optional<Stock> result = stockRepository.findById(id);
         return result.map(Stock::getAmount).orElse(0);
    }

    /**
     * Allows to add {@param amount} product with {@param id}
     * @param id
     * @param amount
     * @return optional updated stock
     */
    public Optional<Stock> addStock(int id, int amount) {
        try {
            int newAmount = Math.max(getStockAmountById(id) + amount, 0);
            Stock stock = new Stock();
            stock.setId(id);
            stock.setAmount(newAmount);
            return Optional.of(stockRepository.save(stock));
        } catch(Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Allows to take product from stock out.
     * @param id
     * @param amount
     * @return optional updated stock
     */
    public Optional<Stock> takeStock(int id, int amount) {
        return addStock(id, -amount);
    }

    /**
     * Allows to update stock
     * @param stock
     * @return optional updated stock
     */
    public Optional<Stock> updateStock(Stock stock) {
        return Optional.of(stockRepository.save(stock));
    }

    /**
     * Allows to remove from stock product with {@param id}.
     * @param id
     * @return result of removing
     */
    public boolean removeStockById(int id) {
        try {
            stockRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
