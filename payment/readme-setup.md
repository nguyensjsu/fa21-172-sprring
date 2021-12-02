# Payment Microservice notes

frontend
 - creditcards.html (payment/src/main/resources/templates)
backend
 - springcybersource folder (payment/src/main/java/com/example/springcybersource)
 - PaymentsCommand (payment/src/main/java/com/example/springpayments)
 - PaymentsController (payment/src/main/java/com/example/springpayments)
 - PaymentsRepository (payment/src/main/java/com/example/springpayments)

 my guess is to move the frontend to the frontend/ folder and use Kong Gateway to connect to the payment/ folder and send data over to the API