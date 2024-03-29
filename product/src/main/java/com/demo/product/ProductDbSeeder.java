package com.demo.product;

import com.demo.product.dao.ProductEntity;
import com.demo.product.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDbSeeder {

    private static final Faker faker = new Faker();

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {

        return args -> {
            /* generate 20 product using faker */

            for (int i = 0; i < 20; i++) {

                String name = faker.commerce().productName();
                int price = faker.random().nextInt(100, 1000);
                String productImageUrl = String.format(
                        "https://picsum.photos/id/%s/200/200",
                        faker.random().nextInt(1, 1000)
                );
                long quantity = faker.random().nextInt(1, 100);

                productRepository.save(new ProductEntity(
                        faker.random().nextLong(),
                        name,
                        productImageUrl,
                        price,
                        quantity
                ));
            }
        };
    }
}
