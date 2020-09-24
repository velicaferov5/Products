package com.products.api.service;

import com.products.api.interfaces.ProductRepository;
import com.products.api.model.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest
class ManageServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ManageService manageService;

    @Test
    public void testAddProduct() {
        Product product = newProduct();
        when(productRepository.save(any())).thenReturn(product);
        Product actual = manageService.addOrUpdateProduct(product);
        assertProduct(product, actual);
    }

    @Test
    public void testGetProduct() {
        Product product = newProduct();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        Product actual = manageService.getProductById(0);
        assertProduct(product, actual);
    }

    @Test
    public void testRemoveProduct() {
        manageService.removeProductById(0);
    }

    private Product newProduct() {
        Product product = new Product();
        product.setType("book");
        product.setName("Reality");
        product.setDescription("Book about Reality");
        return product;
    }

    private void assertProduct(Product expected, Product actual) {
        Assert.assertNotNull(expected);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }
}
