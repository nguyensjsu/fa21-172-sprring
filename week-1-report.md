## Navigation
 - [Notes](##notes)
 - [Create REST API](##create-rest-api)
 - [Run Rest API](##run-rest-api)
 - *No fixed header-linking in README file, please use `Ctrl + F` to search.*

## Meeting Time
 - Wednesday, November 10 = 1 hour 45 minutes

## Notes
Card, Customer, and Order objects / tables
repositories for each

use the rest api through the cloud

## Create REST API

1. Create the following classes (Card, Customer, Order) inside the restapi folder:

online-store > src > main > java > com > example > onlinestore > restapi

Card.java

```java
package com.example.onlinestore.restapi;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;

@Entity
@Table()
@Data
@RequiredArgsConstructor
public class Card {
    private @Id @GeneratedValue Long id;
    @Column(nullable = false)
    private String cardnumber;
    @Column(nullable = false)
    private String cardcode;
    private double balance;
    private boolean activated;
    private String status;
}
```

Customer.java

```java
package com.example.onlinestore.restapi;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;

@Entity
@Table()
@Data
@RequiredArgsConstructor
public class Customer {
    private @Id @GeneratedValue Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    private int rewardpoints;
    private String cardnumber;
}
```

Order.java

```java
package com.example.onlinestore.restapi;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;

@Entity
// @Table()
@Data
@RequiredArgsConstructor
public class Order {
    private @Id @GeneratedValue Long id;
    @Column(nullable = false)
    private String drink;
    @Column(nullable = false)
    private String milk;
    @Column(nullable = false)
    private String size;
    // private double total;
    // private String status;
}
```

2. Create the following repositories (CardRepository, CustomerRepository, OrderRepository) inside the restapi folder:

online-store > src > main > java > com > example > onlinestore > restapi

CardRepository.java

```java
package com.example.onlinestore.restapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface CardRepository extends JpaRepository<Card, String> {
    Card findByCardnumber(String cardnumber);
}
```

CustomerRepository.java

```java
package com.example.onlinestore.restapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface CustomerRepository extends JpaRepository<Customer, String> {
    
}
```

OrderRepository.java

```java
package com.example.onlinestore.restapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<Order, String> {
    
}
```

3. Add the MySQL connection to the REST API by adding the following code into `application.properties`:

online-store > src > main > resources

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/online_store
spring.datasource.username=admin
spring.datasource.password=welcome
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

4. Add the `MainController.java` class for the REST API:

online-store > src > main > java > com > example > onlinestore > restapi

```java
package com.example.onlinestore.restapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MainController {
    private CustomerRepository customerRepo;

    @GetMapping("/")
    String home() {
        return "Welcome to our store Gong Cha!";
    }
}
```

## Run Rest API

1. In a terminal run the following commands to build the project:

```
gradle clean
gradle build
```

2. Start up the MySQL container in Docker by running the following commands in the terminal (open Docker before running this) :

```
docker network create --driver bridge test
docker run --network test -d --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0
```

3. In a terminal, log into mysql bash and create the database, user, and grant permissions to the user:

```
docker exec -it mysql bash
mysql --password
`cmpe172`
create database online_store;
create user 'admin'@'%' identified by 'welcome';
grant all on online_store.* to 'admin'@'%';
```

3. Run the REST API locally by running the following command in the terminal:

```
gradle bootRun
```

