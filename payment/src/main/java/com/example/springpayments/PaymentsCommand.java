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
@Table()
@Data
@RequiredArgsConstructor
class PaymentsCommand {

    private @Id @GeneratedValue Long id;
    private String action;
    private String firstname;
    private String lastname;

    private String address;
    private String city;
    private String state;
    private String zip;
    private String phonenumber;
    private String cardnumber;
    private String expmonth;
    private String expyear;
    private String cvv;
    private String email;
    private String notes;

    // accept only 4 types of cards
    // visa, amex, mastercard, and discover
    private String cardtype;

    // for executing payment through CyberSource
    private String ordernumber;
    private String transactionamount;
    private String transactioncurrency;
    private String authid;
    private String authstatus;
    private String captureid;
    private String capturestatus;


    public String firstname(){return firstname;}

    public String lastname(){ return lastname;}

    public String address(){ return address;}

    public String city(){ return city;}

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
