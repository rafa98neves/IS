package project3.producer;

import java.util.Properties;

//import simple producer packages
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.connect.json.JsonSerializer;
import project3.data.Country;
import project3.data.Item;

import javax.json.JsonObject;

public class SimpleProducer {

    public static void main(String[] args) throws Exception{

        String topicName = "DBInfo_topic";

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                JsonSerializer.class);

        Producer<String, JsonNode> producer = new KafkaProducer<String, JsonNode>(props);

        ObjectMapper mapper = new ObjectMapper();
        Country pais = new Country(0,"portugal");
        JsonNode node = mapper.convertValue(pais, JsonNode.class);
        for(int i = 0; i < 10; i++){
            producer.send(new ProducerRecord<>(topicName,"Country",node));
        }

        Item item = new Item(0,"bola",1);
        node = mapper.convertValue(item, JsonNode.class);
        for(int i = 0; i < 10; i++){
            producer.send(new ProducerRecord<>(topicName,"Item",node));
        }

        System.out.println("Message sent successfully to topic " + topicName);
        producer.close();
    }
}