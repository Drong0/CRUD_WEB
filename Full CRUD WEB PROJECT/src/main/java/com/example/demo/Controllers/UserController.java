package com.example.demo.Controllers;

import com.example.demo.Models.Car;
import com.example.demo.Repository.carRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private carRepository carRepo;

    @GetMapping("/car")
    public  String carUpdate(){
        return "carUpdate";
    }
    @PostMapping("/edit")
    public String edit(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Car> cars;
        if (filter != null && !filter.isEmpty()) {
            cars = carRepo.findByNumber(filter);
        } else {
            cars = carRepo.findAll();
        }
        model.put("cars", cars);
        return "carUpdate";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id,
                       String number,
                       String mark,
                       String model,
                       Integer mileage,
                       Float engine,
                       String imageLink,
                       Map<String, Object> models) {

        Optional<Car> car = carRepo.findById(id);
        if (car.isPresent()){
            car.get().setNumber(number);
            car.get().setMark(mark);
            car.get().setModel(model);
            car.get().setEngine(engine);
            car.get().setMileage(mileage);
            car.get().setImageLink(imageLink);
            carRepo.save(car.get());

        }else{
            System.out.println(id + " not found");
        }

        return "carUpdate";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id,
                       Map<String, Object> models) {

        carRepo.deleteById(id);

        return "carUpdate";
    }

}
