package com.orbanz.store.product;

import com.orbanz.store.common.ErrorDetails;
import com.orbanz.store.order.OrderData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Get Product List", notes = "Retriev all Products", response = Product[].class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Product[].class)
    })
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    @ApiOperation(value = "Get Product", notes = "Retreiving the Product with the given ID", response = Product.class)
    public Product retrieveProduct(@ApiParam(required = true, name = "id", value = "ID os the Product") @PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            throw new ProductNotFoundException("id-" + id);
        }

        return product.get();
    }

    @PostMapping("/products")
    @ApiOperation(value = "Create Product", notes = "Create the product with the given data")
    public ResponseEntity<Object> createProduct(@ApiParam(required = true, name = "product", value = "Product data") @RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/products/{id}")
    @ApiOperation(value = "Update Product", notes = "Update the product data with the given ID")
    public ResponseEntity<Object> updateProduct(@ApiParam(required = true, name = "product", value = "Product data") @RequestBody Product product, @ApiParam(required = true, name = "id", value = "ID os the Product") @PathVariable long id) {
        Optional<Product> originalProduct = productRepository.findById(id);

        if (!originalProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        product.setId(id);

        productRepository.save(product);

        return ResponseEntity.noContent().build();
    }
}
