package com.example.gongcha.restapi;

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
public class MainController {
    private TicketRepository ticketRepository;// our main object to manage
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CardRepository cardRepository;
    private HashMap<String, Ticket> orders = new HashMap<>();

    MainController(CustomerRepository customerRepository, CardRepository cardRepository, TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.cardRepository = cardRepository;
        this.ticketRepository = ticketRepository;
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


    //Ping method - check status of server
    @GetMapping(value={"/","/ping"})
    String home() {
        return "Welcome to Gong Cha!";
    }
//==================================================================================================
// Card related calls
    //Create new card with randomized cardnumber/cardcode and add to database
    //return new card with default attributes ($20 balance, not activated)
    @PostMapping("/cards")
    Card newCard() {
        Card newcard = new Card();
        Random random = new Random();
        int num = random.nextInt(900000000) + 100000000;
        int code = random.nextInt(900) + 100;

        newcard.setCardnumber(String.valueOf(num));
        newcard.setCardcode(String.valueOf(code));
        newcard.setBalance(20.00);
        newcard.setActivated(false);
        newcard.setStatus("New Card");
        return cardRepository.save(newcard);
    }

    //Get all the cards in database
    @GetMapping("/cards")
    List<Card> allCards() {
        return cardRepository.findAll();
    }

    //Get a specific card with cardnumber
    //argument: 9 digits card number 
    @GetMapping("/card/{num}")
    Card getOne(@PathVariable String num, HttpServletResponse response) {
        Card card = cardRepository.findByCardnumber(num);
        if (card == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
        }
        return card;
    }

    //Activate one card already in database with cardnumber
    //if card is not found in db, return error message
    @PostMapping("/card/activate/{num}/{code}")
    Card activate(@PathVariable String num, @PathVariable String code, HttpServletResponse respose) {
        Card card = cardRepository.findByCardnumber(num);
        if (card == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
        if (card.getCardcode().equals(code)) {
            card.setActivated(true);
            cardRepository.save(card);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error. Card Not Valid!");
        }
        return card;
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
    Ticket getActiveOrder(@PathVariable String regid, HttpServletResponse response) {
        Ticket active = orders.get(regid);
        if (active != null) {
            return active;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }

    //
    @DeleteMapping("/order/register/{regid}")
    Message deleteActiveOrder(@PathVariable String regid) {
        Ticket active = orders.get(regid);
        if (active != null) {
            orders.remove(regid);
            Message msg = new Message();
            msg.setStatus("Active Order Cleared");
            return msg;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }

    //
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

//==================================================================================================
//Customer related calls

}