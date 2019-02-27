package com.epam.test.web;

import com.epam.test.exception.CarBrandNotFoundException;
import com.epam.test.exception.CarModelNotFoundException;
import com.epam.test.model.CarBrand;
import com.epam.test.model.CarModel;
import com.epam.test.repository.CarBrandRepository;
import com.epam.test.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CarModelController {

    @Autowired
    private CarModelRepository modelRepository;

    @Autowired
    private CarBrandRepository brandRepository;

    @GetMapping("carModels")
    List<CarModel> getAll() {
        return modelRepository.findAll();
    }

    @GetMapping("/carModels/{carModelId}")
    CarModel getOne(@PathVariable Long carModelId) {
        return modelRepository.findById(carModelId)
                .orElseThrow(() -> new CarModelNotFoundException(carModelId));
    }

    @GetMapping("carBrands/{carBrandId}/carModels")
    Set<CarModel> getByBrandId(@PathVariable Long carBrandId) {
        CarBrand result = brandRepository.findById(carBrandId)
                .orElseThrow(() -> new CarBrandNotFoundException(carBrandId));
        return result.getCarModels();
    }

    @GetMapping("/carBrands/{carBrandId}/carModels/{carModelId}")
    CarModel getByModelIdAndBrandId(@PathVariable Long carBrandId, @PathVariable Long carModelId) {
        return getModel(carModelId, carBrandId);
    }

    @PostMapping("/carModels/{id}")
    CarModel newCarModel(@RequestBody CarModel carModel) {
        return modelRepository.save(carModel);
    }

    @PutMapping("/carModels/{id}")
    CarModel replaceModel(@RequestBody CarModel carModel, @PathVariable Long id) {
        return modelRepository.findById(id)
                .map(c -> {
                    c.setName(carModel.getName());
                    c.setCarBrand(carModel.getCarBrand());
                    return modelRepository.save(carModel);
                })
                .orElseGet(() -> {
                    carModel.setId(id);
                    return modelRepository.save(carModel);
                });
    }

    @DeleteMapping("/carModels/{id}")
    void deleteCarModel(@PathVariable Long id) {
        modelRepository.deleteById(id);
    }

    private CarModel getModel(Long modelId, Long brandId) {
        CarBrand carBrand = brandRepository.findById(brandId)
                .orElseThrow(() -> new CarBrandNotFoundException(brandId));

        return carBrand.getCarModels().stream().filter(c -> c.getId().equals(modelId)).findFirst()
                .orElseThrow(() -> new CarModelNotFoundException(modelId));
    }

}
