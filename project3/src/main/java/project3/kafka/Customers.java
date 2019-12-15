package project3.kafka;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;
import project3.data.Country;
import project3.data.Item;
import project3.data.Sale;

public class Customers {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException, IOException {
        ConsumerRecords<String, JsonNode> records;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Sale sale;
        Random r = new Random();
        Item item;
        Country country;
        List<Item> items = new ArrayList<>();
        List<Country> countries = new ArrayList<>();

        String topicName = "DBInfo_topic";
        String outtopicname = "sales_topic";

        java.util.Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "customers");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                JsonDeserializer.class);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                JsonSerializer.class);

        KafkaConsumer<String, JsonNode> consumer = new KafkaConsumer
                <>(props);

        KafkaProducer<String, JsonNode> producer = new KafkaProducer
                <>(props);

        consumer.subscribe(Arrays.asList(topicName));

        while (true) {
            records = consumer.poll(2000);
            for (ConsumerRecord<String, JsonNode> record : records){
                if (record.value().findValue("payload").findValue("type").asText().equals("item")) items.add(mapper.convertValue(record.value().findValue("payload"), Item.class));
                else if(record.value().findValue("payload").findValue("type").asText().equals("country")) countries.add(mapper.convertValue(record.value().findValue("payload"), Country.class));
            }

            if(items.size() > 0 && countries.size() > 0) {

                item = items.get(r.nextInt(items.size()));
                country = countries.get(r.nextInt(countries.size()));
                sale = new Sale( item, r.nextInt(9) + 1, country);
                System.out.println("New sale generated: " + sale.getItem().getItem_name() + "*" + sale.getUnits() + " to " + sale.getCountry().getCountry_name());

                producer.send(new ProducerRecord<>(outtopicname,"Sale",mapper.convertValue(sale, JsonNode.class)));
            }
            Thread.sleep(2000);
        }
    }
}