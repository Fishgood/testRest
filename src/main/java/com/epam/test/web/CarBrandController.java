package com.epam.test.web;

import com.epam.test.exception.CarBrandNotFoundException;
import com.epam.test.model.CarBrand;
import com.epam.test.repository.CarBrandRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarBrandController {


    private final CarBrandRepository repository;

    public CarBrandController(CarBrandRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/carBrands")
    List<CarBrand> getAll() {
        return repository.findAll();
    }

    @PostMapping("/carBrands")
    CarBrand newCarBrand(@RequestBody CarBrand carBrand) {
        return repository.save(carBrand);
    }

    @GetMapping("/carBrands/{id}")
    CarBrand getOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CarBrandNotFoundException(id));
    }

    @PutMapping("/carBrands/{id}")
    CarBrand replaceBrand(@RequestBody CarBrand carBrand, @PathVariable Long id) {
        return repository.findById(id)
                .map(c -> {
                    c.setName(carBrand.getName());
                    c.setCarModels(carBrand.getCarModels());
                    return repository.save(carBrand);
                })
                .orElseGet(() -> {
                    carBrand.setId(id);
                    return repository.save(carBrand);
                });
    }

    @DeleteMapping("/carBrands/{id}")
    void deleteCarBrand(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
