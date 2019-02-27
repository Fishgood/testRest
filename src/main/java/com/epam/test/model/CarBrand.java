package com.epam.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@Entity
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CarModel> carModels;

    public CarBrand(String name, CarModel... carModels) {
        this.name = name;
        this.carModels = Stream.of(carModels).collect(Collectors.toSet());
        this.carModels.forEach(x -> x.setCarBrand(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBrand carBrand = (CarBrand) o;
        return Objects.equals(id, carBrand.id) &&
                Objects.equals(name, carBrand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
