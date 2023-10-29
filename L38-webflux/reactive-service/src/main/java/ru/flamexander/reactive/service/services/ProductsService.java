package ru.flamexander.reactive.service.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.flamexander.reactive.service.dtos.ProductDto;
import ru.flamexander.reactive.service.entities.Product;
import ru.flamexander.reactive.service.repositories.ProductRepository;

@Service
public class ProductsService {
    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Mono<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Mono<Product> create(ProductDto productDto) {
        Product p = new Product();
        p.setName(productDto.getName());
        return productRepository.save(p);
    }
}
