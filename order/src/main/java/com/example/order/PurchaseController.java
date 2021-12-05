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

        public String getStatus() {
            return status;
        }

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
    @PostMapping("/order/register/{regid}")
    @ResponseStatus(HttpStatus.CREATED)
    Purchase newOrder(@PathVariable String regid, @RequestBody Purchase order) {
        System.out.println("Placing Order (Reg ID =" + regid + ") => " + order);
        if (order.getDrink().equals("") || order.getDrinksize().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order Request!");
        }

        Purchase active = purchases.get(Integer.parseInt(regid));
        if (active != null) {
            System.out.println("Active Order (Reg ID = " + regid + ") +> " + active);
            if (active.getStatus().equals("Ready for Payment.")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order Exists!");
            }
                
        }
        double price = 0.0;
        switch (order.getDrink()) {
        case "Green Tea":
            switch (order.getDrinksize()) {
            case "Medium":
                price = 4.00;
                break;
            case "Large":
                price = 5.00;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "Black Tea":
            switch (order.getDrinksize()) {
            case "Medium":
                price = 4.25;
                break;
            case "Large":
                price = 5.00;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "Thai Tea":
            switch (order.getDrinksize()) {
            case "Medium":
                price = 4.50;
                break;
            case "Large":
                price = 5.00;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        default:
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Drink!");
        }

        double tax = 0.0456;
        double total = price * (price + tax);
        double scale = Math.pow(10,2);
        double rounded = Math.round(total + scale) / scale;
        order.setTotal(rounded);
        order.setStatus("Ready for Payment.");
        Purchase new_order = repo.save(order);
        purchases.add(Integer.parseInt(regid), new_order);
        return new_order;
             
    }

    //Get list of all orders
    @GetMapping("/orders")
    List<Purchase> allOrders() {
        return repo.findAll();
    }

    //Get specific order with ID
    @GetMapping("/order/{regid}")
    Purchase getActiveOrder(@PathVariable String regid, HttpServletResponse response) {
        Purchase active = purchases.get(Integer.parseInt(regid));
        if (active != null) {
            return active;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }

    //Clear paid orders
    @DeleteMapping("/cancel/{regid}")
    Message deleteActiveOrder(@PathVariable String regid) {
        Purchase active = purchases.get(Integer.parseInt(regid));
        if (active != null && active.getStatus().startsWith("Paid With Card")) {
            purchases.remove(regid);
            Message msg = new Message();
            msg.setStatus("Order Cancelled");
            return msg;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }

    /**
    //Pay for an order with an activated card
    @PostMapping("/order/register/{regid}/pay/{cardnum}")
    Card processOrder(@PathVariable String regid, @PathVariable String cardnum) {
        System.out.println("Pay for Order: Reg ID = " + regid + " Using Card  = " + cardnum);
        Purchase active = purchases.get(Integer.parseInt(regid));
        if (active == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
        if (cardnum.equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Number Not Found!");
        }
        if (active.getStatus().startsWith("Paid With Card")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clear Paid Active Order!");
        }
        Card card = cardRepository.findByCardnumber(cardnum);

        if (card == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Found!");
        }
        if (!card.isActivated()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Activated!");
        }
        double price = active.getTotal();
        double balance = card.getBalance();
        if (balance - price < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds on Card!");
        }
        double new_balance = balance - price;
        card.setBalance(new_balance);
        String status = "Paid with Card: " + cardnum + " Balance $" + new_balance + ".";
        active.setStatus(status);
        cardRepository.save(card);
        repo.save(active);
        return card;

    }
    **/


}