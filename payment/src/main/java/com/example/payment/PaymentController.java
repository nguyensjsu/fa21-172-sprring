package com.example.payment;

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
public class PaymentController {
    private PaymentRepository repo;

    PaymentController(PaymentRepository repo) {
        this.repo = repo;
    }

     //Ping method - check status of server
     @GetMapping(value={"/","/ping"})
     String home() {
         return "Welcome to Gong Cha! - Payment management";
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
}