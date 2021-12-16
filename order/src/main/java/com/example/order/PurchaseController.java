package com.example.order;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@RestController
public class PurchaseController {

    private PurchaseRepository repo;
    private List<Purchase> purchases;

    PurchaseController(PurchaseRepository repo) {
        this.repo = repo;
        purchases = new ArrayList<Purchase>();
    }

    class Message {
        private String status;

        //public String getStatus() {
        //    return status;
        //}

        public void setStatus(String msg) {
            status = msg;
        }
    }

    //Ping method - check status of purchase server
    @GetMapping(value={"/","/ping"})
    String home() {
        return "Welcome to Gong Cha - Purchase Management!";
    }


    //==================================================================================================
//Order related calls
    //Submit an order
    @PostMapping("/order/register")
    @ResponseStatus(HttpStatus.CREATED)
    Purchase newOrder( @RequestBody Purchase order) {
        if(purchases.isEmpty()) {
            purchases.add(order);
        }
        order.setOrderNumber(purchases.size());
        System.out.println("Placing Order (Reg ID =" + order.getOrderNumber() + ") => " + order);
        if (order.getDrink().equals("") || order.getDrinkSize().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order Request!");
        }

        Purchase active = purchases.get(order.getOrderNumber()-1);
        /**
        if (active != null) {
            System.out.println("Active Order (Reg ID = " + order.getOrderNumber()  + ") +> " + active);
            if (active.getStatus().equals("Ready for Payment.")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order Exists!");
            }
                
        }
         **/
        String milk = "no milk";
        double price = 0.0;
        switch (order.getDrink()) {
        case "Green Tea":
            switch (order.getDrinkSize()) {
            case "Medium":
                price = 4.00;
                switch(order.getMilk()){
                    case "milk":
                        milk = "milk";
                    case "no milk":
                        milk = "no milk";
                }
                break;
            case "Large":
                price = 5.00;
                switch(order.getMilk()){
                    case "milk":
                        milk = "milk";
                    case "no milk":
                        milk = "no milk";
                }
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "Black Tea":
            switch (order.getDrinkSize()) {
            case "Medium":
                price = 4.25;
                switch(order.getMilk()){
                    case "milk":
                        milk = "milk";
                    case "no milk":
                        milk = "no milk";
                }
                break;
            case "Large":
                price = 5.00;
                switch(order.getMilk()){
                    case "milk":
                        milk = "milk";
                    case "no milk":
                        milk = "no milk";
                }
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "Thai Tea":
            switch (order.getDrinkSize()) {
            case "Medium":
                price = 4.50;
                switch(order.getMilk()){
                    case "milk":
                        milk = "milk";
                    case "no milk":
                        milk = "no milk";
                }
                break;
            case "Large":
                price = 5.00;
                switch(order.getMilk()){
                    case "milk":
                        milk = "milk";
                    case "no milk":
                        milk = "no milk";
                }
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        default:
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Drink!");
        }

        double tax = 1.0456;
        double total = price * tax;
        double scale = Math.pow(10,3);
        double rounded = Math.round(total);
        order.setTotal(rounded);
        order.setStatus("Ready for Payment.");
        order.setMilk(milk);
        Purchase new_order = repo.save(order);
        purchases.add(order.getOrderNumber() , new_order);
        return new_order;
             
    }

    //Get list of all orders
    @GetMapping("/orders")
    List<Purchase> allOrders() {
        return repo.findAll();
    }


    /***
    //Get specific order with ID
    @GetMapping("/order")
    Purchase getActiveOrder(@RequestBody Purchase order, HttpServletResponse response) {
        Purchase active = purchases.get(Integer.parseInt(order.getOrderNumber()));
        if (active != null) {
            return active;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }
    **/

    /**
    //Clear paid orders
    @DeleteMapping("/cancel")
    Message deleteActiveOrder(@RequestBody Purchase order) {
        Purchase active = purchases.get(Integer.parseInt(order.getOrderNumber() ));
        if (active != null && active.getStatus().startsWith("Paid With Card")) {
            purchases.remove(order.getOrderNumber() );
            Message msg = new Message();
            msg.setStatus("Order Cancelled");
            return msg;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }

**/
}