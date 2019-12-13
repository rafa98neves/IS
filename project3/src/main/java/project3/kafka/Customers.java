package project3.kafka;

import java.io.IOException;
import java.util.*;

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

    private static final String tablename = "sales";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException, IOException {
        ConsumerRecords<String, JsonNode> records;

        Sale sale;
        Random r = new Random();
        List<Item> items = new ArrayList<>();
        List<Country> countries = new ArrayList<>();

        String topicName = "DBInfo_topic";
        String outtopicname = "sales_topic";

        java.util.Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                JsonDeserializer.class);

        KafkaConsumer<String, JsonNode> consumer = new KafkaConsumer
                <String, JsonNode>(props);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                JsonSerializer.class);

        KafkaProducer<String, JsonNode> producer = new KafkaProducer
                <String, JsonNode>(props);

        consumer.subscribe(Arrays.asList(topicName));

        while (true) {
            records = consumer.poll(2000);
            for (ConsumerRecord<String, JsonNode> record : records){
                if(record.key().equals("Item")) items.add(mapper.convertValue(record.value(), Item.class));
                else if(record.key().equals("Country")) countries.add(mapper.convertValue(record.value(), Country.class));
            }

            if(items.size() > 0 && countries.size() > 0) {
                sale = new Sale(items.get(r.nextInt(items.size())), r.nextInt(9) + 1, countries.get(r.nextInt(countries.size())));
                System.out.println("New sale generated: " + sale.getItem().getName() + "*" + sale.getUnits() + " from " + sale.getCountry().getCountry());

                producer.send(new ProducerRecord<String,JsonNode>(outtopicname,"Sale",mapper.convertValue(sale, JsonNode.class)));
            }
            Thread.sleep(2000);
        }
    }
}