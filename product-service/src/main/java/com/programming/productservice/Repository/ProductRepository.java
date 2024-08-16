package com.programming.productservice.Repository;

import com.programming.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {

    Optional<Product> findById(String id);
}
