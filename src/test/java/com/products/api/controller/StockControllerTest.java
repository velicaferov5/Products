package com.products.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.api.interfaces.StockRepository;
import com.products.api.model.Product;
import com.products.api.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class StockControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    void getStockAmount() throws Exception {
        Stock stock = newStock();
        when(stockRepository.findById(anyInt())).thenReturn(Optional.of(stock));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/stock/get/0").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void addStock() throws Exception {
        Stock stock = newStock();
        when(stockRepository.save(any())).thenReturn(stock);
        String stringJson = new ObjectMapper().writeValueAsString(stock);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/stock/in/1/1").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void takeStock() throws Exception {
        Stock stock = newStock();
        when(stockRepository.save(any())).thenReturn(stock);
        String stringJson = new ObjectMapper().writeValueAsString(stock);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/stock/out/1/1").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateStock() throws Exception {
        Stock stock = newStock();
        stock.setId(1);
        when(stockRepository.save(any())).thenReturn(stock);
        String stringJson = new ObjectMapper().writeValueAsString(stock);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/stock/update").contentType(MediaType.APPLICATION_JSON).content(stringJson);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void removeStock() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/api/stock/remove/0").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private Stock newStock() {
        Stock stock = new Stock();
        stock.setAmount(1);
        return stock;
    }
}