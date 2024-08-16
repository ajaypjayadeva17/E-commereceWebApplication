package com.programming.productservice.Controller;

// Create a controller advice to handle exceptions globally.

import com.programming.productservice.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleProductNotFoundException(ProductNotFoundException ex) {

        //This line returns the message from the exception,
        //which will be included in the HTTP response body.
        return ex.getMessage();
    }

}
