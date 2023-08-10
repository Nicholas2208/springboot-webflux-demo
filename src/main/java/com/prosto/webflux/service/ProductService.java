package com.prosto.webflux.service;

import com.prosto.webflux.dao.ProductDao;
import com.prosto.webflux.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao dao;

    public List<Product> loadAllProducts() {
        long start = System.currentTimeMillis();
        List<Product> products = dao.getProducts();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return products;
    }

    public Flux<Product> loadAllProductsStream() {
        long start = System.currentTimeMillis();
        Flux<Product> customers = dao.getProductsStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }
}
