package com.orbanz.store.order;

import com.orbanz.store.common.ErrorDetails;
import com.orbanz.store.product.Product;
import com.orbanz.store.product.ProductNotFoundException;
import com.orbanz.store.product.ProductRepository;
import io.swagger.annotations.*;
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

@RestController
@Api("/orders")
public class OrderController {

    @Autowired
    OrderDataRepository orderDataRepository;

    @Autowired
    ProductRepository productRepository;

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
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

    @PostMapping("/orders")
    @ApiOperation(value = "Place order")
    @ApiResponse(code = 401, message = "The created order")
    public ResponseEntity<Object> placeOrder(@ApiParam(required = true, name = "webOrder", value = "Order data") @RequestBody WebOrder webOrder) {
        OrderData orderData = new OrderData(webOrder.getBuyerEmail(), webOrder.getOrderPlaced());
        for (Long productId : webOrder.getProducts()) {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if(!optionalProduct.isPresent()) {
                throw new ProductNotFoundException("id-" + productId);
            }
            OrderItem orderItem = new OrderItem(optionalProduct.get().getName(), optionalProduct.get().getPrice());
            orderData.addOrderItem(orderItem);
        }
        OrderData savedOrderData = orderDataRepository.save(orderData);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedOrderData.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/orders")
    @ApiOperation(value = "Find orders between fromDate and ToDate", notes = "Retrieving the collection of orders", response = OrderData[].class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = OrderData[].class)
    })
    public List<OrderData> getOrdersBetween(@RequestParam(required = false) Date fromDate, @RequestParam(required = false) Date toDate) {
        if(fromDate != null && toDate != null) {
            return orderDataRepository.findByOrderPlacedBetween(fromDate, toDate);
        } else if (fromDate == null && toDate != null) {
            return orderDataRepository.findByOrderPlacedLessThanEqual(toDate);
        } else if (fromDate != null && toDate == null) {
            return orderDataRepository.findByOrderPlacedGreaterThanEqual(fromDate);
        } else {
            return orderDataRepository.findAll();
        }
    }

    @ApiOperation(value = "Get Order Data", notes = "Retreiving the data for the given id", response = OrderData.class)
    @GetMapping("/orders/{id}")
    public OrderData retrieveOrder(@ApiParam(required = true, name = "id", value = "ID os the Order") @PathVariable long id) {
        Optional<OrderData> order = orderDataRepository.findById(id);

        if(!order.isPresent()) {
            throw new OrderNotFoundException("id-" + id);
        }

        return order.get();
    }

}
