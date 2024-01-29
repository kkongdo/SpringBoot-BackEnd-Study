package com.example.firstobject.dto;

import com.example.firstobject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeForm {
    private  Long id;
    private String name;
    private Long Price;

    public Coffee toEntity() {
       return  new Coffee(id, name, Price);
    }
}
