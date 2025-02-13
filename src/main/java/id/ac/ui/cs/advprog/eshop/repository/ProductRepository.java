package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public void delete(String productId) {
        Product deletedProduct = findById(productId);
        if(deletedProduct != null) {
            productData.remove(deletedProduct);
        }
    }

    public Product edit(Product editedProduct, String productId) {
        Product existingProduct = findById(productId);
        if (existingProduct != null) {
            existingProduct.setProductName(editedProduct.getProductName());
            existingProduct.setProductQuantity(editedProduct.getProductQuantity());
        }
        return existingProduct;
    }
}