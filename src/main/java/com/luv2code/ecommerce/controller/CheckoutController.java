package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.luv2code.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Enable Cross-Origin Resource Sharing (CORS) to allow requests from a different domain (e.g., Angular app on localhost:4200)
@CrossOrigin("http://localhost:4200")
// Marks this class as a REST controller, meaning it handles HTTP requests
@RestController
// Base URL mapping for all endpoints in this controller
@RequestMapping("api/checkout")
public class CheckoutController {

    // Service for handling checkout-related business logic
    private CheckoutService checkoutService;

    // Constructor-based dependency injection of the CheckoutService
    @Autowired
    private CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // HTTP POST endpoint to handle purchase requests
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        // Pass the purchase object to the service to process the order
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        // Return the response containing order confirmation details
        return purchaseResponse;
    }
}
