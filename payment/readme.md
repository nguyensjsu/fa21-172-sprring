# Payment Deployment

1. Open Docker Desktop.

2. Open a terminal and navigate to the `payment/` folder.

3. Create a new Docker network by running the following commands:

```
docker network ls
docker network create --driver bridge payment-network
```

4. Create a Docker image by running the following command:

```
docker build -t payment-api .
```

5. Create the MySQL container by running the following command:

```
docker run -d --network payment-network --name payment-mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0
```

6. Open the MySQL container's terminal on Docker (the CLI), and enter in the following in the terminal to create the `payment` database:

```
mysql --password
`cmpe172`
show databases;
create database payment;
show databases;
use payment;
```

7. Create the Payment container by running the following command:

```
docker run --network payment-network -e "MYSQL_HOST=mysql" --name payment-api -td -p 80:8080 payment-api
```

8. In a browser, open [http://localhost/](http://localhost/) to view the webpage.