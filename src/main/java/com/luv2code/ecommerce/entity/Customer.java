package com.luv2code.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

// Marks this class as a JPA entity, which maps to a database table
@Entity
// Specifies the table name in the database
@Table(name = "customer")
// Lombok annotations to generate getter and setter methods
@Getter
@Setter
public class Customer {

    // Primary key for the Customer table
    @Id
    // Specifies that the ID is auto-generated by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Maps the field to the "id" column in the database
    private Long id;

    // Maps this field to the "first_name" column in the database
    @Column(name = "first_name")
    private String firstName;

    // Maps this field to the "last_name" column in the database
    @Column(name = "last_name")
    private String lastName;

    // Maps this field to the "email" column in the database
    @Column(name = "email")
    private String email;

    // One-to-Many relationship between Customer and Order
    // - CascadeType.ALL ensures that operations on the Customer entity (like save or delete) cascade to related Order entities
    // - mappedBy indicates the "customer" field in the Order entity owns this relationship
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    // Helper method to associate an Order with this Customer
    public void add(Order order) {
        // Ensure the order is not null
        if (order != null) {
            // Lazily initialize the orders set if it's null
            if (orders == null) {
                orders = new HashSet<>();
            }
            // Add the order to the customer's set of orders
            orders.add(order);
            // Set the customer field in the Order entity to establish the bidirectional relationship
            order.setCustomer(this);
        }
    }
}