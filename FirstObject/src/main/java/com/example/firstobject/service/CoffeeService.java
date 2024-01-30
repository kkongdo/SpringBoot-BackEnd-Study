package com.example.firstobject.service;

import com.example.firstobject.dto.CoffeeForm;
import com.example.firstobject.entity.Coffee;
import com.example.firstobject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    CoffeeRepository coffeeRepository;

    public List<Coffee> showAll(){
        return coffeeRepository.findAll();
    }
    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto){
        Coffee coffee = dto.toEntity();
        if(coffee.getId() != null){
            return null;
        }
        return coffeeRepository.save(coffee);
    }
    public Coffee update(Long id, CoffeeForm dto){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        Coffee target = dto.toEntity();
        if(target.getId() != id || coffee == null){
            return null;
        }
        target.patch(coffee);
        Coffee save = coffeeRepository.save(target);
        return save;
    }
    public Coffee delete(Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null){
            return null;
        }
        coffeeRepository.delete(coffee);
        return coffee;
    }
}
