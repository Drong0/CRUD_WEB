package com.example.demo.Repository;

import com.example.demo.Models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface carRepository extends CrudRepository<Car, Integer> {
    List<Car> findByNumber(String number);
}
