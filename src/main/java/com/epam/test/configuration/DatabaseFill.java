package com.epam.test.configuration;

import com.epam.test.model.CarBrand;
import com.epam.test.model.CarModel;
import com.epam.test.repository.CarBrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DatabaseFill {

    @Bean
    CommandLineRunner initDatabase(CarBrandRepository repository) {
        return args -> {

            log.info("Preloading: " + repository.save(new CarBrand("Droid",
                    new CarModel("r2d2"),
                    new CarModel("citripio"),
                    new CarModel("BB-8")
            )));

            log.info("Preloading: " + repository.save(new CarBrand("BMW",
                    new CarModel("x5"),
                    new CarModel("i7"),
                    new CarModel("m3")
            )));
            log.info("Preloading: " + repository.save(new CarBrand("Audi",
                    new CarModel("A6"),
                    new CarModel("r8"),
                    new CarModel("q8")
            )));
        };
    }


}
