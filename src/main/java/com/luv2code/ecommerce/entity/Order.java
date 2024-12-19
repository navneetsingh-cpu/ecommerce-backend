package com.luv2code.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// Marks this class as a JPA entity that maps to a database table
@Entity
// Specifies the name of the table as "orders"
@Table(name = "orders")
// Lombok annotations to automatically generate getter and setter methods
@Getter
@Setter
public class Order {

    // Primary key for the Order table
    @Id
    // Specifies that the ID is auto-generated by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Maps the field to the "id" column in the database
    private Long id;

    // Maps the field to the "order_tracking_number" column
    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    // Maps the field to the "total_quantity" column
    @Column(name = "total_quantity")
    private int totalQuantity;

    // Maps the field to the "total_price" column
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    // Maps the field to the "status" column
    @Column(name = "status")
    private String status;

    // Automatically captures the date and time when the order is created
    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    // Automatically updates the timestamp when the order entity is updated
    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    // One-to-Many relationship with OrderItem
    // - CascadeType.ALL ensures operations on Order cascade to its OrderItems
    // - mappedBy indicates the "order" field in the OrderItem entity owns this relationship
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    // Many-to-One relationship with Customer
    // - Specifies the foreign key column "customer_id"
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // One-to-One relationship with Address for shipping address
    // - CascadeType.ALL ensures cascading operations
    // - JoinColumn specifies the foreign key column "shipping_address_id"
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;


    // One-to-One relationship with Address for billing address
    // - CascadeType.ALL ensures cascading operations
    // - JoinColumn specifies the foreign key column "billing_address_id"
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    // Helper method to add an OrderItem to this Order
    public void add(OrderItem item) {
        // Ensure the item is not null
        if (item != null) {
            // Lazily initialize the orderItems set if it's null
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }
            // Add the item to the set and establish the bidirectional relationship
            orderItems.add(item);
            item.setOrder(this);
        }
    }
}
