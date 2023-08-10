package com.prosto.webflux.controller;

import com.prosto.webflux.dto.Product;
import com.prosto.webflux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAllProducts() {
         return service.loadAllProducts();
    }

    @GetMapping(value = "/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getAllCustomersStream() {
        return service.loadAllProductsStream();
    }
}
