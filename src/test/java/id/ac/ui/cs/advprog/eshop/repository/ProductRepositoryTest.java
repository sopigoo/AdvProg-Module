package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {}
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        String productId = "d3f8e3a2-5b46-4c9b-9c3f-8a2e9d4e3b6f";
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bagus");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.edit(updatedProduct, productId);
        assertNotNull(result);
        assertEquals("Sampo Cap Bagus", result.getProductName());
        assertEquals(20, result.getProductQuantity());
    }

    @Test
    void testEditNonExistentProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Suko");
        updatedProduct.setProductQuantity(30);

        Product result = productRepository.edit(updatedProduct, "d3f8e3a2-5b46-4c9b-9c3f-8a2e9d4e3b6f");
        assertNull(result);
    }

    @Test
    void testEditProductWhenMoreThanOneExists() {
        Product product1 = new Product();
        String productId1 = "d3f8e3a2-5b46-4c9b-9c3f-8a2e9d4e3b6f";
        product1.setProductId(productId1);
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        String productId2 = "a7b2c5d8-9e6f-4a3b-b2d9-f4e5c6a7b8d9";
        product2.setProductId(productId2);
        product2.setProductName("Sampo Cap Budi");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usup");
        updatedProduct.setProductQuantity(15);

        Product result = productRepository.edit(updatedProduct, productId1);
        assertNotNull(result);
        assertEquals("Sampo Cap Usup", result.getProductName());
        assertEquals(15, result.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        String productId = "a7b2c5d8-9e6f-4a3b-b2d9-f4e5c6a7b8d9";
        product.setProductId(productId);
        product.setProductName("Sampo Cap Kila");
        product.setProductQuantity(5);
        productRepository.create(product);

        productRepository.delete(productId);

        assertNull(productRepository.findById(productId));
        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testDeleteNonExistentProduct() {
        productRepository.delete("a7b2c5d8-9e6f-4a3b-b2d9-f4e5c6a7b8d9");
        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testDeleteWhenMoreThanOneExist() {
        Product product1 = new Product();
        String productId1 = "d3f8e3a2-5b46-4c9b-9c3f-8a2e9d4e3b6f";
        product1.setProductId(productId1);
        product1.setProductName("Sampo Cap Banang");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        String productId2 = "a7b2c5d8-9e6f-4a3b-b2d9-f4e5c6a7b8d9";
        product2.setProductId(productId2);
        product2.setProductName("Sampo Cap Alang");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        productRepository.delete(productId1);

        assertNull(productRepository.findById(productId1));
        Product remainingProduct = productRepository.findById(productId2);
        assertNotNull(remainingProduct);
        assertEquals(productId2, remainingProduct.getProductId());

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());
        assertEquals(productId2, iterator.next().getProductId());
        assertFalse(iterator.hasNext());
    }
}