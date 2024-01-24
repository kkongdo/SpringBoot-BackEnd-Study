package com.example.firstobject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private int password;

    public Member(Long id, String email, int password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Member() {

    }
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }
}
