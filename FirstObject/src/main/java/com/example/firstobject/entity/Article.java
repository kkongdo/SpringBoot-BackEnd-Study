package com.example.firstobject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Article {
    @GeneratedValue
    @Id
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
}
