package org.voiculescu.orderservice.boostrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.orderservice.entity.OrderHeader;
import org.voiculescu.orderservice.repository.OrderHeaderRepository;

import java.util.Optional;

@Component
@Slf4j
public class Boostrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        OrderHeader orderHeader = orderHeaderRepository.findById(1L).orElse(null);
        orderHeader.getOrderLines().forEach(ol -> {
            log.info(ol.getProduct().getDescription());
            ol.getProduct().getCategories().forEach(cat -> log.info("{}\n", cat.getDescription()));
        });
    }
}
