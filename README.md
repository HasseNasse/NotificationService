# Running the application
1. Start the Kafka broker and ZooKeeper and Notification service
`docker-compose up`
2. Start the Notification UI to receive SSEs
`cd notification-ui && npm start`
3. Produce some messages
```
docker exec -it notification-service_kafka_1 bash
./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic notifications-topic
> Hello
> Hello2 
```
