package com.example.demo.model.user;

import lombok.*;

import javax.persistence.*;

@Data

@NoArgsConstructor
@Entity(name = "userr")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;

    public User(String name) {
        this.name = name;
    }
}
