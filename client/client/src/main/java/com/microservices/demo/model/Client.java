package com.microservices.demo.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "person_id")
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @NotNull
    @Size(min =8, max = 8)
    private String password;

    private String state;
    
}
