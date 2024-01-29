package com.example.firstobject.api;

import com.example.firstobject.dto.CoffeeForm;
import com.example.firstobject.entity.Coffee;
import com.example.firstobject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoffeeApiController {
    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping("/api/coffees")
    public List<Coffee> showAll(){
        return  coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeForm form){
        Coffee coffee = form.toEntity();
       return  coffeeRepository.save(coffee);
    }

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm form){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        Coffee target = form.toEntity();
        if(target.getId() != id || coffee == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        target.patch(coffee);
        Coffee save = coffeeRepository.save(target);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(save);
    }
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffeeRepository.delete(coffee);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
