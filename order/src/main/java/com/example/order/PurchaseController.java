package com.example.order;

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

    private OrderRepository repo;
    private List<Purchase> purchases;

    PaymentController(OrderRepository repo) {
        this.repo = repo;
        purchases = new List<Purchase>();
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
    Ticket newOrder(@PathVariable String regid, @RequestBody Ticket order) {
        System.out.println("Placing Order (Reg ID =" + regid + ") => " + order);
        if (order.getDrink().equals("") || order.getMilk().equals("") ||
        order.getDrinksize().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order Request!");
        }

        Ticket active = orders.get(regid);
        if (active != null) {
            System.out.println("Active Order (Reg ID = " + regid + ") +> " + active);
            if (active.getStatus().equals("Ready for Payment.")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order Exists!");
            }
                
        }
        double price = 0.0;
        switch (order.getDrink()) {
        case "Milktea":
            switch (order.getDrinksize()) {
            case "Small":
                price = 2.95;
                break;
            case "Regular":
                price = 3.65;
                break;
            case "Large":
            case "Your Own Cup":
                price = 3.95;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "Tea":
            switch (order.getDrinksize()) {
            case "Small":
                price = 2.25;
                break;
            case "Regular":
                price = 2.65;
                break;
            case "Large":
            case "Your Own Cup":
                price = 2.95;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "Coffe":
            switch (order.getDrinksize())  {
            case "Small":
                price = 3.45;
                break;
            case "Regular":
                price = 4.15;
                break;
            case "Large":
            case "Your Own Cup":
                price = 4.45;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "Smoothie":
            switch (order.getDrinksize()) {
            case "Small":
                price = 4.25;
                break;
            case "Regular":
                price = 4.65;
                break;
            case "Large":
            case "Your Own Cup":
                price = 4.95;
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
        Ticket new_order = ticketRepository.save(order);
        orders.put(regid, new_order);
        return new_order;
             
    }

    //Get list of all orders
    @GetMapping("/orders")
    List<Ticket> allOrders() {
        return ticketRepository.findAll();
    }

    //Get specific order with ID
    @GetMapping("/order/register/{regid}")
    Ticket getActiveOrder(@PathVariable String regid, fHttpServletResponse response) {
        Ticket active = orders.get(regid);
        if (active != null) {
            return active;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }

    //Clear paid orders
    @DeleteMapping("/order/register/{regid}")
    Message deleteActiveOrder(@PathVariable String regid) {
        Ticket active = orders.get(regid);
        if (active != null && active.getStatus().startsWith("Paid With Card")) {
            orders.remove(regid);
            Message msg = new Message();
            msg.setStatus("Paid Active Order Cleared");
            return msg;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }

    //Pay for an order with an activated card
    @PostMapping("/order/register/{regid}/pay/{cardnum}")
    Card processOrder(@PathVariable String regid, @PathVariable String cardnum) {
        System.out.println("Pay for Order: Reg ID = " + regid + " Using Card  = " + cardnum);
        Ticket active = orders.get(regid);
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
        ticketRepository.save(active);
        return card;

    }


}