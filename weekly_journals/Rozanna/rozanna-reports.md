# Rozanna's reports

## Navigation
 - [10/13](##10/13)
 - [11/4](##11/4)
 - [11/10](##11/10)
 - [11/22](##11/22)
 - [11/24](##11/24)
 - [12/1](##12/1)
 - [12/5](##12/5)
 - [12/6](##12/6)
 - [12/8](##12/8)
 - [12/xx](##12/xx)
 - *No fixed header-linking in README file, please use `Ctrl + F` to search.*

## 10/13
formed dm group on discord for the team
chose team name

## 11/4
group call to talk about the project
split up work in monolithic structure

## 11/10
group call to talk about the project
setting up the API
setting up MySQL db
talking about frontend stuff

## 11/22
dm discussion about the project
Nhu and I on call about monilithic vs microservice structure

team journals as individual .md files for each member
included in main README.md file

submit on 12/6:
project source code
present project on 12/6
10-20 min presentation
can show up to present only for our team presentation (we are 9th)
S&A about project distribution, commit number, who did what, etc.

## 11/24
group call to talk about the project
talked about how to split up work
how microservices connect to frontend
how to split up payments backend and frontend

found out payment processing goes to backend, implement API / frontend on top of it

## 12/1
worked on payments some more

## 12/5
group call to talk about the project
need to create
    - frontend
        - create customer dashboard with menu
        - settings page to reset user password
        - copy and paste payment submission form
        - can view past orders (history)
    - back office dashboard
        - login only
        - reset password request
        - table of orders

frontend -> kong gateway -> rest apis -> micro services?

frontend talks to kong gateway through http request - axois in react

worked on payment/ folder on changing lab 7 material into a rest api

prioritizing what can be done in the limited time the team has left (20 hours left)

## 12/6
group call to talk about the project
tried to piece together the project
gave demo, got feedback that only 5% was done
decided to keep working on it till Friday 12/10
    - need to ping prof about availability to demo again
    - want to ask if can also demo next Friday 12/17 if not enough progress shown by this friday

run frontend:
    - cd `\fa21-172-sprring\frontend\my-app\`
    - run `npm install`
    - cd `\fa21-172-sprring\frontend\`
    - run `npm install`
run any backend folder:
    - cd `\fa21-172-sprring\[FOLDER NAME]\`
    - run `gradle build`
    - run `gradle bootRun`

After signing up a new user, go to `http://localhost:8080/customers` to see the new customer that just signed up

after logging in with new user account, should add routing to customer dashboard

added Payment.js, Payment.css, payment form

need to change Menu.js to pull drink data instead of hardcoding the drink data, so that other components can pull it

## 12/8
Wednesday
group call to talk about the project
scheduling to work on project and quizzes and final exam
    - Friday - work on quiz part 2
    - 

add
    - shopping cart / place to view what user wants to purchase
        - how to add to cart?
    - finish @RestControllers for 
        - payment/
        - order/

start the order, pay the payment, set the order as paid, show order number to user
frontend creates order
frontend passes order info to payment
frontend receives payment confirmation

customer buys 1 drink at a time

plan to work with Shayanna to connect `payment/` to `frontend/`
getAllPayments() returns error message or order number
    error message if an error occurred
    order number if payment was successfull

meeting diagram - high level to low level

![diagram.png](images/diagram.png)

## 12/xx