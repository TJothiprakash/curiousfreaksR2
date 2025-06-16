package lowleveldesign.messagebrokersystem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Broker broker = new Broker();
        broker.createTopic("orders", 3);

        Producer producer = new SimpleProducer(broker);
        Consumer consumer1 = new SimpleConsumer(broker, "consumer-1");
        Consumer consumer2 = new SimpleConsumer(broker, "consumer-2");

        consumer1.subscribe("orders", "order-group");
        consumer2.subscribe("orders", "order-group");

        producer.send("orders", "msg1", "Order #1 created");
        producer.send("orders", "msg2", "Order #2 created");
        producer.send("orders", "msg3", "Order #3 created");

        List<Message> c1Messages = consumer1.poll("orders", "order-group");
        System.out.println("Consumer 1 received: " + c1Messages);

        List<Message> c2Messages = consumer2.poll("orders", "order-group");
        System.out.println("Consumer 2 received: " + c2Messages);
    }
}

/* LLD 1: Scalable Message Broker System (like Kafka)
✅ Features to Design:
Publish/Subscribe model

Topics & Partitions

Message Retention

At-least-once delivery (or exactly-once, if ambitious)

Consumer groups

Scalability and fault-tolerance

🧱 Core Components:
Broker: Stores messages, manages topics/partitions.

Producer: Sends messages to a topic.

Consumer: Reads messages from a topic.

Controller: Manages cluster metadata (like Kafka’s Zookeeper initially).

Partition Manager: Handles load balancing & replication.

Suggested Flow to Implement:
Topic creation → /createTopic?name=xyz&partitions=3

Producer sends → /publish?topic=xyz&message=Hello

Consumer subscribes → /subscribe?topic=xyz&groupId=abc

Consumer polls → /poll?topic=xyz&groupId=abc

Retention logic: Memory/Disk and time/size-based cleanup

🚦 Optional: Add acknowledgement mechanism for delivery guarantees.
*/