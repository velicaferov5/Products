package com.products.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.api.interfaces.ProductRepository;
import com.products.api.model.Product;
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
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    void testGetProduct() throws Exception{
        Optional<Product> product = Optional.of(newProduct());
        when(productRepository.findById(anyInt())).thenReturn(product);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/product/get/0").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testAddProduct() throws Exception{
        Product product = newProduct();
        when(productRepository.save(any())).thenReturn(product);
        String stringJson = new ObjectMapper().writeValueAsString(product);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/product/new").contentType(MediaType.APPLICATION_JSON).content(stringJson);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"type\":\"book\",\"name\":\"Reality\",\"description\":\"Book about Reality\"}"));
    }

    @Test
    void testRemoveProduct() throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/api/product/remove/0").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    Product newProduct() {
        Product product = new Product();
        product.setType("book");
        product.setName("Reality");
        product.setDescription("Book about Reality");
        return product;
    }
}
