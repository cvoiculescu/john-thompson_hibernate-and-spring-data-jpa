package org.voiculescu.orderservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.orderservice.entity.OrderHeader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.orderservice")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Test
    @Transactional
    void createOrderHeader() {
        OrderHeader orderHeader = new OrderHeader().setCustomerName("Customer");
        OrderHeader saved = orderHeaderRepository.save(orderHeader);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreatedDate()).isNotNull();
    }

    @Test
    void retrieveOrder() {
        OrderHeader byId = orderHeaderRepository.findById(1L).orElse(null);
        assertThat(byId).isNotNull();
    }

    @Test
    @Transactional
    void updateOrder() {
        OrderHeader saved = orderHeaderRepository.save(new OrderHeader().setCustomerName("Customer"));
        orderHeaderRepository.flush();
        saved.setCustomerName("Customer1");
        orderHeaderRepository.save(saved);
        orderHeaderRepository.flush();
        assertThat(orderHeaderRepository.getReferenceById(saved.getId()).getCustomerName()).isEqualTo("Customer1");
    }

}
