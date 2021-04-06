# Sample for Kafka Messaging in Microservices
This is for a sample using Kafka as a Messaging system to organize the communication among the event-based microservices

# Architecture diagram
Below the diagram that shows how the microservices communicate with each others.

![B2C architecture diagram using kafka](b2c_architecture.png)

# Installation
`mvn clean install -DskipTests`

# Deployment
Locally, we can start those applications
- localhost:8085 => account
- localhost:8086 => order
- localhost:8087 => product
- localhost:8088 => shipping
- localhost:8089 => customer

# Tests
