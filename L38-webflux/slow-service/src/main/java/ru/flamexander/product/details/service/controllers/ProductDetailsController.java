package ru.flamexander.product.details.service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.flamexander.product.details.service.dtos.ProductDetailsDto;

@RestController
@RequestMapping("/api/v1/details")
public class ProductDetailsController {
    @GetMapping("/{id}")
    public ProductDetailsDto getProductDetailsById(@PathVariable Long id) throws InterruptedException {
//        - Примечание: детали платежа в сервисе деталей просто генерируются на ходу, не обязательно добавлять БД.
//                Чтобы сымитировать в таком случае отсутствие записи, может добавить какое-нибудь условие на id (например,
//                не будут находиться все детали для продуктов, у который id четный).
        if ((id % 2) == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }else{ return new ProductDetailsDto(id, "Product"+id+" description..");}
    }
}
