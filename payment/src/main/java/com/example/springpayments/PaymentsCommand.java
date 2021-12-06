package com.example.springpayments;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = @Index(name = "altIndex", columnList = "ID", unique = true))
@Data
@RequiredArgsConstructor
class PaymentsCommand {

    @Column(nullable = false)
    private @Id @GeneratedValue Long id;
    @Column(nullable = false)
    private String action;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zip;
    @Column(nullable = false)
    private String phonenumber;
    @Column(nullable = false)
    private String cardnumber;
    @Column(nullable = false)
    private String expmonth;
    @Column(nullable = false)
    private String expyear;
    @Column(nullable = false)
    private String cvv;
    private String email;
    private String notes;

    // accept only 4 types of cards
    // visa, amex, mastercard, and discover
    @Column(nullable = false)
    private String cardtype;

    // for executing payment through CyberSource
    @Column(nullable = false)
    private String ordernumber;
    @Column(nullable = false)
    private String transactionamount;
    @Column(nullable = false)
    private String transactioncurrency;
    @Column(nullable = false)
    private String authid;
    @Column(nullable = false)
    private String authstatus;
    @Column(nullable = false)
    private String captureid;
    @Column(nullable = false)
    private String capturestatus;
    @Column(nullable = false)
    private String cost;

    public String firstname() {
        return firstname;
    }

    public String lastname() {
        return lastname;
    }

    public String address() {
        return address;
    }

    public String city() {
        return city;
    }

    public String state() {
        return state;
    }

    public String zip() {
        return zip;
    }

    public String phone() {
        return phonenumber;
    }

    public String cardnum() {
        return cardnumber;
    }

    public String cardexpmon() {
        return expmonth;
    }

    public String cardexpyear() {
        return expyear;
    }

    public String cardcvv() {
        return cvv;
    }

    public String email() {
        return email;
    }

    public String notes() {
        return notes;
    }
}
