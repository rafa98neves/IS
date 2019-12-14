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
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import project3.data.Country;
import project3.data.Item;
import project3.data.Purchase;
import project3.data.Sale;


public class PurchaseOrders {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException, IOException {
        ConsumerRecords<String, JsonNode> records;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Purchase purchase;
        Random r = new Random();
        List<Item> items = new ArrayList<>();

        String topicName = "DBInfo_topic";
        String outtopicname = "purchases_topic";

        java.util.Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "purchase");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
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
            for (ConsumerRecord<String, JsonNode> record : records) {
                if (record.value().findValue("payload").findValue("type").asText().equals("item")) items.add(mapper.convertValue(record.value().findValue("payload"), Item.class));
            }

            if (items.size() > 0) {
                purchase = new Purchase(items.get(r.nextInt(items.size())), r.nextInt(9) + 1 );
                System.out.println("New purchase generated: " + purchase.getItem().getItem_name() + "*" + purchase.getUnits());
                producer.send(new ProducerRecord<>(outtopicname, "Purchase", mapper.convertValue(purchase, JsonNode.class)));
            }
            Thread.sleep(2000);
        }
    }
}