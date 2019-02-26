package com.epam.test.web;

import com.epam.test.model.CarModel;
import com.epam.test.repository.CarModelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarModelController {


    private final CarModelRepository repository;

    public CarModelController(CarModelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/carModels")
    List<CarModel> getAll() {
        return repository.findAll();
    }

    @PostMapping("/carModels/{id}")
    CarModel newCarModel(@RequestBody CarModel carModel) {
        return repository.save(carModel);
    }
}
