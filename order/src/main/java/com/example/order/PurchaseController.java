package com.example.order;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
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
    @CrossOrigin(origins = "*")
    @GetMapping(value={"/","/ping"})
    String home() {
        return "Welcome to Gong Cha - Purchase Management!";
    }


    //==================================================================================================
//Order related calls
    //Submit an order
    @PostMapping("/order/register")
    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.CREATED)
    String newOrder( @RequestBody Purchase order) {
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
        String milk = "";
        double price = 0.0;
        switch (order.getDrink()) {
        case "Green Tea":
            switch (order.getDrinkSize()) {
            case "Medium":
                price = 4.00;
                break;
            case "Large":
                price = 5.00;
                break;
            default:
                return "Invalid Size!";
            }
            break;
        case "Black Tea":
            switch (order.getDrinkSize()) {
            case "Medium":
                price = 4.25;
                break;
            case "Large":
                price = 5.00;
                break;
            default:
                return "Invalid Size!";
            }
            break;
        case "Thai Tea":
            switch (order.getDrinkSize()) {
            case "Medium":
                price = 4.50;
                break;
            case "Large":
                price = 5.00;
                break;
            default:
                return  "Invalid Size!";
            }
            break;
        default:
            return "Invalid Drink!";
        }
        switch(order.getMilk()){
            case "milk":
                milk = "milk";
                break;
            case "no milk":
                milk = "no milk";
                break;
            default:
                return "Invalid Milk!";
        }
        double tax = 1.0456;
        double total = price * tax;
        double scale = Math.pow(10,3);
        double rounded = Math.round(total*100.0)/100.0;
        order.setTotal(rounded);
        order.setStatus("Ready for Payment.");
        order.setMilk(milk);
        Purchase new_order = repo.save(order);

        purchases.add(order.getOrderNumber() , new_order);
        return "Successful Order! the order number is -" + order.getOrderNumber().toString()+"- and the cost is $"+
                order.getTotal();
             
    }

    //Get list of all orders
    @GetMapping("/orders")
    @CrossOrigin(origins = "*")
    List<Purchase> allOrders() {
        return repo.findAll();
    }



    //Get specific order with ID
    @GetMapping("/getorder")
    Purchase getActiveOrder(@RequestBody Integer orderNum, HttpServletResponse response) {
        Purchase active = purchases.get(orderNum);
        if (active != null) {
            return active;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }


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