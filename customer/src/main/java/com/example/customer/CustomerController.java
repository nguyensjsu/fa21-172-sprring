package com.example.customer;

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
public class CustomerController {
    
    @Autowired
    private CustomerRepository repository;
    private HashMap<String, Customer> customers;
  
    CustomerController(CustomerRepository repository) {
        this.repository = repository;
        customers = new HashMap<>();
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


    //Ping method - check status of customer server
    @GetMapping(value={"/","/ping"})
    String home() {
        return "Welcome to Gong Cha! - Customer Management";
    }


//==================================================================================================
//Customer related calls
    //Get all customers
    @GetMapping("/customers")
    public List<Customer> all() {
        return repository.findAll();
    }

    //Create new Customer with user's sign up info
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer newCustomer(@RequestBody Customer customer, HttpServletResponse response) {
        //check if someone with provided first and last name already has an account
        if(customers.get(customer.getFirstname()+customer.getLastname()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already has an account!");
        }
        
        //if no old account is found, create a new one
        Customer newCustomer = new Customer();
        
        newCustomer.setFirstname(customer.getFirstname());
        newCustomer.setLastname(customer.getLastname());
        newCustomer.setUsername(customer.getUsername());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPassword(customer.getPassword());
        
        //add to hashmap
        customers.put(newCustomer.getFirstname() + newCustomer.getLastname(), newCustomer);
        
        repository.save(newCustomer);
        return newCustomer;
    } 

    //Get specific Customer with first and last name
    @GetMapping("/customers/{firstname}/{lastname}")
    Customer getActiveOrder(@PathVariable String firstname, @PathVariable String lastname, HttpServletResponse response) {
        Customer active = customers.get(firstname.concat(lastname));
        if (active != null) {
            return active;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Not Found!");
        }
    }

    //Delete Customer
    @DeleteMapping("/order/register/{regid}")
    Message deleteActiveOrder(@PathVariable String regid) {
        Customer active = customers.get(Integer.parseInt(regid));
        if (active != null) {
            customers.remove(regid);
            Message msg = new Message();
            msg.setStatus("Customer Removed");
            return msg;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Not Found!");
        }
    }

}
