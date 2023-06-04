package org.voiculescu.orderservice.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.orderservice.entity.Product;
import org.voiculescu.orderservice.entity.ProductStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.orderservice")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void createProduct() {
        Product product = new Product()
                .setDescription("MyDescription");
        Product saved = productRepository.save(product);
        assertNotNull(saved);
        assertThat(saved.getProductStatus()).isEqualTo(ProductStatus.NEW);
        assertNotNull(saved.getCreatedDate());
        assertNotNull(saved.getLastModifiedDate());
    }

    @Test
    @Transactional
    void updateProduct() {
        Product product = new Product().setDescription("MyDescription");
        Product saved = productRepository.save(product);
        saved.setProductStatus(ProductStatus.DISCONTINUED);
        productRepository.flush();
        assertThat(productRepository.getReferenceById(saved.getId()).getProductStatus()).isEqualTo(ProductStatus.DISCONTINUED);
    }

    @Test
    @Transactional
    void deleteProduct() {
        Product product = new Product().setDescription("MyDescription");
        Product saved = productRepository.save(product);
        productRepository.deleteById(saved.getId());
        productRepository.flush();
        assertThrows(EntityNotFoundException.class,()->productRepository.getReferenceById(saved.getId()));
    }

}
