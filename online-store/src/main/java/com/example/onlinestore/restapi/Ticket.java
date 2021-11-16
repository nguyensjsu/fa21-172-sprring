package com.example.onlinestore.restapi;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;

/**
 * An order ticket object in the database, is named different from "Order" to avoid conflict with MySQL reserved keyword
 */

@Entity
@Table
@Data
@RequiredArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Integer id;
    private double total;
    private String status;
    @Column(nullable=false) private String drink;
    @Column(nullable=false) private String milk;
    @Column(nullable=false) private String drinksize;                
}
