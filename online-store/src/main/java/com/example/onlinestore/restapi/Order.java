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

@Entity
@RequiredArgsConstructor
public class Order {
    private @Id @GeneratedValue Long id;
    @Column(nullable=false) private String drink;
    @Column(nullable=false) private String milk;
    @Column(nullable=false) private String size;
    private double total;
    private String status;
}
