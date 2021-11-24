package com.example.customer;

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
@Table
@Data
@RequiredArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Integer id;
    private int rewardpoints;
    private String cardnumber;
    @Column(nullable=false) private String firstname;
    @Column(nullable=false) private String lastname;
                            
}
