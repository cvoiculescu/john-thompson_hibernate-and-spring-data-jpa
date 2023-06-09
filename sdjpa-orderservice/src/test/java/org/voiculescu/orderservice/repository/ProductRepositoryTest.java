package org.voiculescu.orderservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.orderservice.entity.Product;
import org.voiculescu.orderservice.entity.ProductStatus;
import org.voiculescu.orderservice.services.ProductService;
import org.voiculescu.orderservice.services.ProductServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.orderservice")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductService productService;

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
    void deleteProduct() {
        Product product = new Product().setDescription("MyDescription");
        Product saved = productRepository.save(product);
        productRepository.deleteById(saved.getId());
        assertThrows(JpaObjectRetrievalFailureException.class, () -> productRepository.getReferenceById(saved.getId()));
    }

    @Test
    void findByDescriptionTest() {
        Product product = productRepository.findByDescription("PRODUCT1").orElse(null);
        assertNotNull(product);
        assertNotNull(product.getCategories());
        assertThat(product.getCategories().size()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void updateQuantityTest() {
        Product product = productRepository.findByDescription("PRODUCT1").orElse(null);
        product.setQuantityOnHand(20);
        Product saved = productRepository.saveAndFlush(product);
        log.info("Updated quantity: {}", saved.getQuantityOnHand());
        assertThat(saved.getQuantityOnHand()).isEqualTo(20);
    }

    @Test
    void addAndUpdateProductTest() {
        Product product = new Product().setDescription("My Product");
        Product saved = productService.save(product);
        Product saved2 = productService.updateQOH(saved.getId(), 25);
        assertThat(saved2.getQuantityOnHand()).isEqualTo(25);
    }

}
