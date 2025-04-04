package com.microservices.demo.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]{10}$")
    private String dni;

    private String gender;
    private int age;
    private String address;
    private String phone;

}
