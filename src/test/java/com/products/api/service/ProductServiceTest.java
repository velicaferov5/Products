package com.products.api.service;

import com.products.api.interfaces.ProductRepository;
import com.products.api.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetProduct() {
        Optional<Product> product = Optional.of(newProduct());
        when(productRepository.findById(anyInt())).thenReturn(product);
        assertTrue(productService.getProductById(0).isPresent());
        assertProduct(product.get(), productService.getProductById(0).get());
    }

    @Test
    void addProduct() {
        Product product = newProduct();
        when(productRepository.save(any())).thenReturn(product);
        Product actual = productService.addOrUpdateProduct(product);
        assertProduct(product, actual);
    }

    @Test
    void removeProduct() {
        assertTrue(productService.removeProductById(0));
    }

    Product newProduct() {
        Product product = new Product();
        product.setType("book");
        product.setName("Reality");
        product.setDescription("Book about Reality");
        return product;
    }

    void assertProduct(Product expected, Product actual) {
        assertTrue((expected == null && actual == null) || (expected != null && actual != null) );
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }
}
