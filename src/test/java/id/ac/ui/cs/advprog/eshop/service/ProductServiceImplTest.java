package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private String productId;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID().toString();
        product = new Product();
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> retrievedProducts = productService.findAll();

        assertEquals(2, retrievedProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        when(productRepository.findById(productId)).thenReturn(product);

        Product foundProduct = productService.findById(productId);

        assertNotNull(foundProduct);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete(productId);

        productService.delete(productId);

        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testEditProduct() {
        when(productRepository.edit(product, productId)).thenReturn(product);

        Product editedProduct = productService.edit(product, productId);

        assertNotNull(editedProduct);
        verify(productRepository, times(1)).edit(product, productId);
    }
}
