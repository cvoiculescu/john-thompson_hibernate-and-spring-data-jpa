package org.voiculescu.orderservice.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.orderservice.entity.Product;
import org.voiculescu.orderservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public Product updateQOH(Long id, Integer quantityOnHand) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.setQuantityOnHand(quantityOnHand);
        return productRepository.saveAndFlush(product);
    }
}
