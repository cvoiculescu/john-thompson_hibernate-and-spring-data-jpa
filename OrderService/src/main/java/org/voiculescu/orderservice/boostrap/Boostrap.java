package org.voiculescu.orderservice.boostrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.orderservice.entity.Customer;
import org.voiculescu.orderservice.entity.CustomerRepository;
import org.voiculescu.orderservice.entity.OrderHeader;
import org.voiculescu.orderservice.repository.OrderHeaderRepository;

import java.util.Optional;

@Component
@Slf4j
public class Boostrap implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    private void readOrderData() {
        OrderHeader orderHeader = orderHeaderRepository.findById(1L).orElse(null);
        orderHeader.getOrderLines().forEach(ol -> {
            log.info(ol.getProduct().getDescription());
            ol.getProduct().getCategories().forEach(cat -> log.info("{}\n", cat.getDescription()));
        });
    }

    private void testingVersion() {
        Customer customer = new Customer().setName("Testing Version");
        Customer savedCustomer1 = customerRepository.saveAndFlush(customer);
        log.info("Customer version: {}", savedCustomer1.getVersion());
        savedCustomer1.setName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.saveAndFlush(savedCustomer1);
        log.info("Customer version: {}", savedCustomer2.getVersion());
        savedCustomer2.setName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.saveAndFlush(savedCustomer2);
        log.info("Customer version: {}", savedCustomer3.getVersion());
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        readOrderData();
        testingVersion();
    }
}
