package com.programming.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "vendors")
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "vendor")
    private List<Product> products;

//    @OneToMany(mappedBy = "vendor")
//    private List<Order> orders;
//
//    @OneToMany(mappedBy = "vendor")
//    private List<VendorRating> vendorRatings;
}
