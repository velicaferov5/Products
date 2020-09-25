package com.products.api.controller;

import com.products.api.interfaces.OrderRepository;
import com.products.api.interfaces.StockRepository;
import com.products.api.model.Order;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    void getOrder() throws Exception {
        Optional<Order> order = Optional.of(newOrder(Order.State.RUNNING));
        when(orderRepository.findById(anyInt())).thenReturn(order);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/order/get/0").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cancelOrder() throws Exception {
        Optional<Order> order = Optional.of(newOrder(Order.State.CANCELLED));
        when(orderRepository.findById(any())).thenReturn(order);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/order/cancel/0").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private Order newOrder(Order.State state) {
        Order order = new Order();
        order.setProducts(newProducts(5));
        order.setState(state);
        return order;
    }

    private Map<Product, Integer> newProducts(int count) {
        Map<Product, Integer> products = new HashMap<>();
        for (int index=0; index<count; index++) {
            products.put(newProduct(), 1);
        }
        return products;
    }

    Product newProduct() {
        Product product = new Product();
        product.setType("book");
        product.setName("Reality");
        product.setDescription("Book about Reality");
        return product;
    }

    private Stock newStock() {
        Stock stock = new Stock();
        stock.setAmount(10);
        return stock;
    }
}