package com.example.firstobject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Long price;

    public void patch(Coffee coffee) {
        if(coffee.name != null){
            this.name = coffee.name;
        }
        if(coffee.price != null){
            this.price = coffee.price;
        }
    }
}
