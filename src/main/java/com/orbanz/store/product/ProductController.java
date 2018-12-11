package com.orbanz.store.product;

import com.orbanz.store.common.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ProductNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/products")
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product retrieveProduct(@PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);

        if(!product.isPresent()) {
            throw new ProductNotFoundException("id-" + id);
        }

        return product.get();
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {
        Optional<Product> originalProduct = productRepository.findById(id);

        if(!originalProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        product.setId(id);

        productRepository.save(product);

        return ResponseEntity.noContent().build();
    }
}
