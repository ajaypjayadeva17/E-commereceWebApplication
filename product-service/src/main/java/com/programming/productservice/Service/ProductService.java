package com.programming.productservice.Service;

import com.programming.productservice.model.Product;
import com.programming.productservice.Exceptions.ProductNotFoundException;
import com.programming.productservice.Repository.ProductRepository;
import com.programming.productservice.dto.ProductRequest;
import com.programming.productservice.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProductService {

    private final ProductRepository productRepository;

    // Quick Tip
    // Why ProductRequest Class didn't imported and
    // How can we are able to Pass the object of this class to createProduct method

    // Ans -  " because it's a parameter passed from the controller layer"
    public void createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public List<ProductResponse> findProductById(String id) {
        try
        {
            Optional<Product> products = productRepository.findById(id);
            if(products.isPresent())
            {
                return products.stream().map(this::mapToProductResponse).toList();
            }
            else
                throw new ProductNotFoundException("Product" + id + "not found ");
        } catch (Exception e) {
            log.error("Error occurred while finding product by id: {}", id, e);
            throw new RuntimeException("Failed to find product by id: " + id, e);
        }


    }

    public void updatePartialProductDetailsById(String id, ProductRequest productRequest) {

        log.info(" updatePartialProductDetailsById -> ");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        // We used JAVA 8 Features instead of normal if else condition
        //    if (productRequest.getName() != null && !productRequest.getName().isEmpty()) {
        //        product.setName(productRequest.getName());
        //    }

        // Update fields if they are not null or empty in the request
        Optional.ofNullable(productRequest.getName())
                .filter(name -> !name.isEmpty())
                .ifPresent(product::setName);

        Optional.ofNullable(productRequest.getDescription())
                .filter(description -> !description.isEmpty())
                .ifPresent(product::setDescription);

        Optional.ofNullable(productRequest.getPrice())
                .ifPresent(product::setPrice);

        // Add checks for other fields similarly

        // Save the updated product back to the database
        productRepository.save(product);

        log.info(" <- updatePartialProductDetailsById ");
    }

    public void deleteProductById(String id) {
        log.info(" deleteProductById -> ");
        // ifPresentOrElse: This method takes two arguments -
        // a Consumer to execute if the value is present and
        // a Runnable to execute if the value is not present.

        productRepository.findById(id).ifPresentOrElse(
                // The Consumer lambda deletes the product if it exists, and
                    product -> {
                        productRepository.deleteById(id);
                        log.info("Product with ID {} deleted successfully", id);
                    },

                // The Runnable lambda throws a ProductNotFoundException if it does not.
                    () -> {
                        throw new ProductNotFoundException("Product with ID " + id + " not found");
                    }
                );
        log.info(" <- deleteProductById ");
    }


    public void deleteAllProducts() {
        log.info("deleteAllProducts ->");
        productRepository.deleteAll();
        log.info("<- deleteAllProducts");
    }
}
