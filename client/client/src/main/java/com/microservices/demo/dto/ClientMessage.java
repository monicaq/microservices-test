package com.microservices.demo.dto;

import lombok.Data;

@Data
public class ClientMessage {
    private Long idClient;
    private String name;
    private String dni;
    
}
