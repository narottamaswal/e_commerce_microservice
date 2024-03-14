This is 
### Spring Cloud Dependencies:
This project utilizes Spring Cloud dependencies such as Feign clients, API gateways, and Eureka server for service discovery and communication management within a microservices architecture.

### Internal Communication:
Feign clients are employed for internal communication between services, facilitating seamless interaction and data exchange within the microservices ecosystem. RabbitMQ queues are utilized for both synchronous and asynchronous communication, ensuring efficient message handling and delivery.
![image](https://github.com/narottamaswal/e_commerce_microservice/assets/65083220/f118f69f-cd96-4137-88c1-36d043011ba4)


## Application Services 


### API Gateway Service:
The API Gateway service leverages Spring Cloud API Gateway to establish secure communication, both internally and externally, within the microservices architecture. It enhances security by implementing API keys, ensuring authorized access and adding an extra layer of protection to the system's endpoints.

### Product Service:
The Produce service serves as an inventory handler and provides details of all products within the system, facilitating inventory management functionalities within the microservices architecture.

### Order Service:
The Order service is responsible for creating and fetching user orders, managing the ordering process seamlessly within the microservices ecosystem, ensuring efficient order handling and retrieval.

### Customer Service:
The Customer service manages customer-related functionalities within the microservices architecture, handling user data and interactions to provide personalized services and user experiences.

### Notification Service:
The Notification service is designed to send notifications via various channels such as email and SMS, providing users with timely updates and alerts based on specific events or triggers within the system.

### Eureka Server:
Eureka Server acts as the service registry and discovery server within the microservices architecture, facilitating dynamic service registration and discovery, ensuring efficient communication and interaction between services.


### Future work
Adding ELK stack (Elastic search, logstash and kibana) for standarized logging and zipkin for distributed tracking
Containzering the project with docker and deploying on K8S
Using Reddis cache for inventory management
A
