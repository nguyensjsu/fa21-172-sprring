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
@Table(indexes=@Index(name = "altIndex", columnList = "cardnumber", unique = true))
@Data
@RequiredArgsConstructor
public class Card {
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Integer id;
    private double balance;
    private boolean activated;
    private String status;
    @Column(nullable=false) private String cardnumber;
    @Column(nullable=false) private String cardcode;
                              
}
