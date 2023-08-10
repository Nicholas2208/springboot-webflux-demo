package com.prosto.webflux.dao;

import com.prosto.webflux.dto.Product;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

@Component
public class ProductDao {
    private String[]  productNames = {"Чикен Бургер",
            "Биг Спешиал Ростбиф", "Плов рыбный",
            "Двойной Чизбургер", "Биг Спешиал",
            "Чикен Премьер", "Наггетсы",
            "Тост с ветчиной", "Пирожок с кудябликами",
            "Клецки свекольные"
    };

    public List<Product> getProducts() {
          return IntStream
                  .rangeClosed(1, productNames.length)
                  .peek(ProductDao::sleepExecution)
                  .peek(i -> System.out.println("processing count : " + i))
                  .mapToObj(i -> new Product(i, productNames[i-1]))
                  .collect(Collectors.toList());
    }

    public Flux<Product> getProductsStream() {
        return Flux
                .range(1, productNames.length)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count : " + i))
                .map(i -> new Product(i, productNames[i-1]));
    }

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
