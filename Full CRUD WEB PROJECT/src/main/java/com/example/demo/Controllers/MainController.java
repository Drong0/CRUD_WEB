package com.example.demo.Controllers;


import com.example.demo.Models.Car;
import com.example.demo.Repository.carRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private carRepository carRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Car> cars = carRepo.findAll();
        model.put("cars", cars);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Car> cars;
        if (filter != null && !filter.isEmpty()) {
            cars = carRepo.findByNumber(filter);
        } else {
            cars = (List<Car>) carRepo.findAll();
        }
        model.put("cars", cars);
        return "main";
    }
    @PostMapping("/save")
    public String save(@RequestParam String number, String mark, String model, Integer mileage, Float engine,
                       String imageLink, Map<String, Object> models) {
        carRepo.save(new Car(mark, model, engine, mileage, number, imageLink ));
        return "main";
    }
    @GetMapping("/home")
    public String home(Map<String, Object> model) {
        return "home";
    }
}




