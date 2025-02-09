package com.programming.userauthservice.dto;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserDetailsDto {
    private String name;
    private String email;
    private String city;
    private String role;
    private String message;
    private int statusCode;
    private List<Map<String, String>> ourCustomersList;
}
