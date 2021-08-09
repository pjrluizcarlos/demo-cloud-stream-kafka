# Demo for Spring Cloud Stream + Kafka + Mongo DB

### Requirements to run
- [docker & compose](https://docs.docker.com/compose/install/)

### Kafka and Mongo DB infrastructure

Run docker-compose commands by script (chmod only in first execution)
```
cd ./demo-cloud-stream-kafka
chmod +x ./infra-restart.sh 
./infra-restart.sh
```

**OR** 

Run docker-compose manually:
```
docker-compose -f ./kafka/docker-compose.yml down
docker-compose -f ./mongodb/docker-compose.yml down
docker-compose -f ./kafka/docker-compose.yml up -d
docker-compose -f ./mongodb/docker-compose.yml up -d
```

### The Problem

```
Successful sale
- Given I have enough products for a sale
- When I create a sale
- Then the stock quantity for this product should be updated 
- Then the sale status should be updated to "on the way"

Sale with not enough quantity of product
- Given I not have enough products for a sale
- When I create a sale
- Then the sale status should be updated to "not enough quantity"
```

### The Solution

There are two applications in this repository:
- `product-service`
- `sale-service`

`product-service` is responsible for:
- Create and Find products through REST API
- Validate product quantity and existence on sale event
- Manage product stock quantity

`sale-service` is responsible for:
- Create and find sales through REST API
- Update sale status after product validation

### Execution:
1. Create a **new product** using [product-service REST API](http://localhost:8020/swagger-ui.html).  
2. Create a **new sale** using [sale-service REST API](http://localhost:8021/swagger-ui.html).  
3. New sale creation results in an event produced to `sales-created-v1` topic.  
4. Events in `sales-created-v1` topic are processed by `product-service`, validating the product quantity x stock quantity.
5. If valid or not, an event is sent to `sales-updated-v1`, with a new status.
6. `sales-updated-v1` is consumed by `sale-service`, **updating the new status in database**.

### Technologies

- **Spring Cloud Stream** as stream framework
- **Spring for Apache Kafka** as binder
- **Spring Boot Starter Web** for web interface
- **Spring Boot Data Mongo DB** for repository interface with data-source
- **JUnit 5 with Mockito** for unit/integration tests
- **Lombok** for to avoid boiler-plating

### Pending points

- If used an **invalid product ID on sale creation**, will result in a dead letter queue event on topic sales-created-dlq-v1. This event will not be handled.
- Implement **reactive programming** in this example
- Create **integration tests**
- Create **unit tests**

### Reference Documentation

For further reference, please consider the following sections:

- [Confluent quickstart with docker](https://docs.confluent.io/platform/current/quickstart/ce-docker-quickstart.html)
- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/#build-image)
- [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-kafka)
- [Avenue Code - Processing Messages with Spring Cloud Stream and Kafka](https://blog.avenuecode.com/processing-messages-with-spring-cloud-stream-and-kafka)
- [Henrique Luis Schmidt@Avenue Code - Spring Cloud Stream Sample](https://github.com/henriquels25/spring-cloud-stream-sample)
- [Baeldung - Introduction to Spring Cloud Stream](https://www.baeldung.com/spring-cloud-stream)
- [Baeldung - Getting Started with Stream Processing with Spring Cloud Data Flow](https://www.baeldung.com/spring-cloud-data-flow-stream-processing)


