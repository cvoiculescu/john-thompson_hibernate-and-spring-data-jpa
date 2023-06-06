package org.voiculescu.orderservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.orderservice.entity.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.orderservice")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderApprovalRepository orderApprovalRepository;

    Product product;
    Customer customer;

    @BeforeEach
    void setUp() {
        product = productRepository.saveAndFlush(new Product()
                .setDescription("Test Product")
                .setProductStatus(ProductStatus.NEW));
        customer = customerRepository.saveAndFlush(new Customer()
                .setName("Customer"));
    }

    @Test
    @Transactional
    void createOrderHeader() {
        OrderHeader orderHeader = new OrderHeader().setCustomer(customer);
        OrderHeader saved = orderHeaderRepository.save(orderHeader);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreatedDate()).isNotNull();
        assertThat(saved.getLastModifiedDate()).isNotNull();
    }

    @Test
    @Disabled
    void retrieveOrder() {
        OrderHeader orderHeader = new OrderHeader().setOrderApproval(new OrderApproval().setApprovedBy("me"));
        orderHeaderRepository.saveAndFlush(orderHeader);
        OrderHeader byId = orderHeaderRepository.findById(1L).orElse(null);
        assertThat(byId).isNotNull();
    }

    @Test
    @Transactional
    void updateOrder() {
        OrderHeader saved = orderHeaderRepository.save(new OrderHeader().setCustomer(customer));
        orderHeaderRepository.save(saved);
        orderHeaderRepository.flush();
        assertThat(orderHeaderRepository.getReferenceById(saved.getId()).getCustomer().getName()).isEqualTo("Customer");
    }

    @Test
    void testOrderWithLine() {

        OrderLine orderLine = new OrderLine()
                .setQuantityOrdered(5)
                .setProduct(product);
        OrderApproval orderApproval = new OrderApproval()
                .setApprovedBy("Approval");
        orderApprovalRepository.save(orderApproval);
        OrderHeader orderHeader = new OrderHeader()
                .setCustomer(customer)
                .setOrderApproval(orderApproval)
                .addOrderLine(orderLine);

        OrderHeader saved = orderHeaderRepository.save(orderHeader);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertNotNull(saved.getOrderLines());
        assertEquals(1, saved.getOrderLines().size());
        assertNotNull(saved.getOrderLines().iterator().next().getId());
    }

}
