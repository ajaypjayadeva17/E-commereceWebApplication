package com.programming.productservice.Controller;

import com.programming.productservice.Service.ProductService;
import com.programming.productservice.dto.ProductRequest;
import com.programming.productservice.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping("/getproductList")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllproducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/getProductById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProductById(@PathVariable String id){
        return productService.findProductById(id);
    }

    @PatchMapping("/updateProductByID/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProductDetailsById(@PathVariable String id,@RequestBody ProductRequest productRequest){
        productService.updatePartialProductDetailsById(id,productRequest);
    }

    @DeleteMapping("/deleteProductById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id){
        productService.deleteProductById(id);
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllProducts(){
        productService.deleteAllProducts();
    }

}
