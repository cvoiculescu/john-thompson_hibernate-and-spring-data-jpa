package org.voiculescu.orderservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.voiculescu.orderservice.entity.*;
import org.voiculescu.orderservice.repository.OrderHeaderRepository;
import org.voiculescu.orderservice.repository.ProductRepository;

import java.util.List;
import java.util.Random;

@Slf4j
@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.orderservice")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {

    public static final String PRODUCT_D1 = "Product 1";
    public static final String PRODUCT_D2 = "Product 2";
    public static final String PRODUCT_D3 = "Product 3";
    public static final String TEST_CUSTOMER = "CUSTOMER";

    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    @Disabled
    @Rollback(false)
    void testLoadData() {
        List<Product> products = loadProducts();
        List<Customer> customers = loadCustomers();
        int ordersToCreate = 10;
        for (int i = 0; i < ordersToCreate; i++) {
            log.info("Creating order #{}", i);
            saveOrder(customers, products);
        }
        orderHeaderRepository.flush();
    }

    private OrderHeader saveOrder(List<Customer> customers, List<Product> products) {
        Random random = new Random();
        OrderHeader orderHeader = new OrderHeader()
                .setCustomer(customers.get(0));
        products.forEach(product ->
                orderHeader.addOrderLine(new OrderLine()
                        .setProduct(product)
                        .setQuantityOrdered(Math.abs(random.nextInt()))
                ));
        return orderHeaderRepository.save(orderHeader);
    }

    private List<Product> loadProducts() {
        return List.of(
                getOrSaveProduct(PRODUCT_D1),
                getOrSaveProduct(PRODUCT_D2),
                getOrSaveProduct(PRODUCT_D3)
        );
    }

    private List<Customer> loadCustomers() {
        return List.of(getOrSaveCustomer(TEST_CUSTOMER));
    }

    private Product getOrSaveProduct(String description) {
        return productRepository.findByDescription(description).orElse(
                productRepository.save(new Product().setDescription(description))
        );
    }

    private Customer getOrSaveCustomer(String name) {
        return customerRepository.findCustomerByNameIgnoreCase(name)
                .orElse(customerRepository.save(
                        new Customer()
                                .setName(name)
                                .setShipping(new Address()
                                        .setAddress("123 Main")
                                        .setCity("New Orleans")
                                        .setState("LA")
                                        .setZipCode("12345")
                                )
                ));
    }

}
