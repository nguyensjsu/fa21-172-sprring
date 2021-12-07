package com.example.springpayments;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Optional;
import java.time.*;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.joran.conditional.ElseAction;
import lombok.extern.slf4j.Slf4j;

import com.example.springcybersource.*;
import org.springframework.beans.factory.annotation.Value;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/")
public class PaymentsController {

    private static boolean DEBUG = true;

    // variables to connect to CyberSource API
    @Value("${cybersource.apihost}")
    private String apiHost;
    @Value("${cybersource.merchantkeyid}")
    private String merchantKeyId;
    @Value("${cybersource.merchantsecretkey}")
    private String merchantsecretKey;
    @Value("${cybersource.merchantid}")
    private String merchantId;

    @Autowired
    private PaymentsRepository respository;

    private CyberSourceAPI api = new CyberSourceAPI();

    private static String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

    private static HashMap<String, String> states = new HashMap<>();

    static {
        states.put("AL", "Alabama");
        states.put("AK", "Alaska");
        states.put("AZ", "Arizona");
        states.put("AR", "Arkansas");
        states.put("CA", "California");
        states.put("CO", "Colorado");
        states.put("CT", "Connecticut");
        states.put("DE", "Delaware");
        states.put("FL", "Florida");
        states.put("GA", "Georgia");
        states.put("HI", "Hawaii");
        states.put("ID", "Idaho");
        states.put("IL", "Illinois");
        states.put("IN", "Indiana");
        states.put("IA", "Iowa");
        states.put("KS", "Kansas");
        states.put("KY", "Kentucky");
        states.put("LA", "Louisiana");
        states.put("ME", "Maine");
        states.put("MD", "Maryland");
        states.put("MA", "Massachusetts");
        states.put("MI", "Michigan");
        states.put("MN", "Minnesota");
        states.put("MS", "Mississippi");
        states.put("MO", "Missouri");
        states.put("MT", "Montana");
        states.put("NE", "Nebraska");
        states.put("NV", "Nevada");
        states.put("NH", "New Hampshire");
        states.put("NJ", "New Jersey");
        states.put("NM", "New Mexico");
        states.put("NY", "New York");
        states.put("NC", "North Carolina");
        states.put("ND", "North Dakota");
        states.put("OH", "Ohio");
        states.put("OK", "Oklahoma");
        states.put("OR", "Oregon");
        states.put("PA", "Pennsylvania");
        states.put("RI", "Rhode Island");
        states.put("SC", "South Carolina");
        states.put("SD", "South Dakota");
        states.put("TN", "Tennesse");
        states.put("TX", "Texas");
        states.put("UT", "Utah");
        states.put("VT", "Vermont");
        states.put("VA", "Virginia");
        states.put("WA", "Washington");
        states.put("WV", "West Virginia");
        states.put("WI", "Wisconsin");
        states.put("WY", "Wyoming");
    }

    @Getter
    @Setter
    class Message {
        private String msg;

        public Message(String m) {
            msg = m;
        }
    }

    class ErrorMessages {
        private ArrayList<Message> messages = new ArrayList<Message>();

        public void add(String msg) {
            messages.add(new Message(msg));
        }

        public ArrayList<Message> getMessage() {
            return messages;
        }

        public void print() {
            for (Message m : messages) {
                System.out.println(m.msg);
            }
        }
    }

    @GetMapping
    public String getAction(@ModelAttribute("command") PaymentsCommand command, Model model) {

        model.addAttribute("message", "Hello World!");
        return "creditcards";

    }

    @PostMapping
    public String postAction(@Valid @ModelAttribute("command") PaymentsCommand command,
            @RequestParam(value = "action", required = true) String action, Errors errors, Model model,
            HttpServletRequest request) {

        // set CyberSource variables
        CyberSourceAPI.setHost(apiHost);
        CyberSourceAPI.setKey(merchantKeyId);
        CyberSourceAPI.setSecret(merchantsecretKey);
        CyberSourceAPI.setMerchant(merchantId);

        CyberSourceAPI.debugConfig();

        // more form validation (validation also exists inside creditcards.html)
        ErrorMessages errMsgs = new ErrorMessages();

        // validate state
        String stateInput = command.getState();
        if (states.get(stateInput) == null) {
            errors.reject("State field must be a 2 letter abbreviation of the 50 U.S. States.");
            errMsgs.add("Invalid state: " + stateInput);
        }

        // validate zip code
        /*
         * source: https://howtodoinjava.com/java/regex/us-postal-zip-code-validation/
         * 
         * '^' start at beginning of string; '[0-9]{5}' match 1 digit 5 times; '(?:'
         * group, don't capture; '-' match '-' character; '[0-9]{4}' match 1 digit 4
         * times; ')' end non-capturing group; '?' make non-capturing group optional;
         * '$' ensure string ends
         */
        String zipInput = command.getZip();
        if (!zipInput.matches("^[0-9]{5}(?:-[0-9]{4})?$")) {
            errors.reject(
                    "Zip code must be either a 5 digit number, or a 9 digit number separated after the 5th digit by a dash.");
            errMsgs.add("Invalid zip code: " + zipInput);
        }

        // validate phone number
        /*
         * source:
         * https://howtodoinjava.com/java/regex/java-regex-validate-and-format-north-
         * american-phone-numbers/
         */
        String phoneInput = command.getPhonenumber();
        if (!phoneInput.matches("^\\(\\d{3}\\) \\d{3}\\-\\d{4}$")) {
            errors.reject("Phone number must be a 10 digit number in the following format: (###) ###-####.");
            errMsgs.add("Invalid phone number: " + phoneInput);
        }

        // validate card number
        String cardNumInput = command.getCardnumber();
        if (!cardNumInput.matches("^[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}$")) {
            errors.reject("Card number must be 12 digits long in the following format: ####-####-####-####.");
            errMsgs.add("Invalid card number: " + cardNumInput);
        }

        // validate card expiration month
        // format must be 01 through 12
        // leading 0 required
        String cardExpMonthInput = command.getExpmonth();
        boolean validExpMonth = false;
        for (int i = 0; i < months.length; i++) {
            // ignore capitalization
            if (cardExpMonthInput.equalsIgnoreCase(months[i])) {
                validExpMonth = true;

                if (i < 10) {
                    command.setExpmonth("0" + Integer.toString(i));
                } else {
                    command.setExpmonth(Integer.toString(i));
                }
            }
        }
        if (!validExpMonth) // if invalid expiration month
        {
            errors.reject("Card expiration month must be the full name of the month.");
            errMsgs.add("Invalid card expiration month: " + cardExpMonthInput);
        }

        // validate card expiration year
        String cardExpYearInput = command.getExpyear();
        if (!cardExpYearInput.matches("^[0-9]{4}$")) {
            errors.reject("Card expiration year must be 4 digits long.");
            errMsgs.add("Invalid card expiration year: " + cardExpYearInput);
        }

        // validate card security code
        // support for visa, mastercard, and discover - 3 digit long security code
        String cardSecurityCodeInput = command.getCvv();
        if (!cardSecurityCodeInput.matches("^[0-9]{3}$")) {
            errors.reject("Card security code must be 3 digits long.");
            errMsgs.add("Invalid card security code: " + cardSecurityCodeInput);
        }

        // validate card type (based on 1st digit of card number)
        // support for:
        // visa: starts with 4; set card type to 001
        // mastercard: starts with 5; set card type to 002
        // discover: starts with 6; set card type to 004
        int cardTypeInput = Integer.parseInt(cardNumInput.substring(0, 1));
        if (cardTypeInput == 4) // visa
        {
            command.setCardtype("001");
        } else if (cardTypeInput == 5) // mastercard
        {
            command.setCardtype("002");
        } else if (cardTypeInput == 6) // discover
        {
            command.setCardtype("004");
        } else {
            errors.reject("Currently accepted credit card types are: Visa, Mastercard, Discover.");
            errMsgs.add("Unsupported credit card type.");
        }

        // display errors OR success message
        if (errors.hasErrors()) {
            // errMsgs.print();
            model.addAttribute("messages", errMsgs.getMessage());
            return "creditcards";
        } else {
            model.addAttribute("message", "Thank you for your payment!");
        }

        // payment process

        // make a random order number
        String orderNum = Integer.toString((int) Math.floor((Math.random() * 1000000) + 1));

        // create AuthRequest object
        AuthRequest auth = new AuthRequest();

        // set all 16 fields
        auth.reference = "Order Number: " + orderNum;
        auth.billToFirstName = command.getFirstname();
        auth.billToLastName = command.getLastname();
        auth.billToAddress = command.getAddress();
        auth.billToCity = command.getCity();
        auth.billToState = command.getState();
        auth.billToZipCode = command.getZip();
        auth.billToPhone = command.getPhonenumber();
        auth.billToEmail = command.getEmail();
        auth.transactionAmount = "30.00";
        auth.transactionCurrency = "USD";
        auth.cardNumnber = command.getCardnumber();
        auth.cardExpMonth = command.getExpmonth();
        auth.cardExpYear = command.getExpyear();
        auth.cardCVV = command.getCvv();
        auth.cardType = command.getCardtype();

        // check authorization / authentication
        boolean authValid = false;
        AuthResponse authResponse = new AuthResponse();
        System.out.println("\n\nAuth Request: " + auth.toJson());
        authResponse = api.authorize(auth);
        System.out.println("\n\nAuth Response: " + authResponse.toJson());
        if (authResponse.status.equals("AUTHORIZED")) {
            authValid = true;
        } else {
            String authResponseErrMsg = authResponse.status + ": " + authResponse.reason + "; " + authResponse.message;
            System.out.println(authResponseErrMsg);
            model.addAttribute("message", authResponseErrMsg);
            return "creditcards";
        }

        // capture data and receive response
        boolean captureValid = false;
        CaptureRequest capture = new CaptureRequest();
        CaptureResponse captureResponse = new CaptureResponse();
        if (authValid) {
            capture.reference = "Order Number: " + orderNum;
            capture.paymentId = authResponse.id;
            capture.transactionAmount = "30.00";
            capture.transactionCurrency = "USD";
            System.out.println("\n\nCapture Request: " + capture.toJson());
            captureResponse = api.capture(capture);
            System.out.println("\n\nCapture Response: " + captureResponse.toJson());
            if (captureResponse.status.equals("PENDING")) {
                captureValid = true;
            } else {
                String captureResponseErrMsg = captureResponse.status + ": " + captureResponse.reason + "; "
                        + captureResponse.message;
                System.out.println(captureResponseErrMsg);
                model.addAttribute("message", captureResponseErrMsg);
                return "creditcards";
            }
        }

        // if auth and capture are successful
        if (authValid && captureValid) {
            // set PaymentsCommand vars for executing CyberSource payments
            command.setOrdernumber(orderNum);
            command.setTransactionamount("30.00");
            command.setTransactioncurrency("USD");
            command.setAuthid(authResponse.id);
            command.setAuthstatus(authResponse.status);
            command.setCaptureid(captureResponse.id);
            command.setCapturestatus(captureResponse.status);

            // save PaymentCommand (payment info) to repository
            respository.save(command);

            // print success message
            String successMsg = "Thank you for your payment! Your order number is: " + orderNum;
            System.out.println(successMsg);
            model.addAttribute("message", successMsg);
        }

        log.info("Action: " + action);
        log.info("Command: " + command);

        return "creditcards";
    }

}