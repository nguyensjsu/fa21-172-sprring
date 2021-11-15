package com.example.onlinestore.restapi;

import java.util.Random;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
public class MainController {
    private CustomerRepository customerRepository;
    private CardRepository cardRepository;
    private TicketRepository ticketRepository;

    MainController(CustomerRepository customerRepository, CardRepository cardRepository, TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.cardRepository = cardRepository;
        this.ticketRepository = ticketRepository;
    }


    @GetMapping(value={"/","/ping"})
    String home() {
        return "Welcome to Gong Cha!";
    }
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

    @GetMapping("/cards")
    List<Card> all() {
        return cardRepository.findAll();
    }

    @GetMapping("/card/{num}")
    Card getOne(@PathVariable String num, HttpServletResponse response) {
        Card card = cardRepository.findByCardnumber(num);
        if (card == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
        }
        return card;
    }

    @PostMapping("/card/activate/{num}/{code}")
    Card activate(@PathVariable String num, @PathVariable String code, HttpServletResponse respose) {
        Card card = cardRepository.findByCardnumber(num);
        if (card == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
        if (card.getCardcode().equals(code)) {
            card.setActivated(true);
            cardRepository.save(card);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Valid!");
        }
        return card;
    }
}
