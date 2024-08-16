package com.programming.productservice.Exceptions;

public class ProductNotFoundException extends RuntimeException{
   public ProductNotFoundException(String message) {
       // calling constructor of the superclass
       super(message);
   }
}
