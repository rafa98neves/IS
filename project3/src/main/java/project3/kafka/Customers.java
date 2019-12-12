package project3.kafka;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import javax.json.Json;


public class Customers implements Serializer<JsonNode>, Deserializer<JsonNode> {

    private ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException, IOException {

        Random r = new Random();
        String topicName = "DBInfo_topic";
        String outtopicname = "sales_topic";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Json> lines = builder.stream(topicName);

        //Escolher um item do topico

        //Escolher um pais do topico

        int units = r.nextInt(100); //Escolher número de unidades ( 0 a 100 )

        KTable<String, Long> outlines = lines.groupByKey().count();
        outlines.mapValues((k,v) -> k + "=>" + v).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        System.out.println("Reading stream from topic " + topicName);
    }


    @Override
    public byte[] serialize(String topic, JsonNode data) {

        try {
            return mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            return new byte[0];
        }
    }

    @Override
    public JsonNode deserialize(String topic, byte[] data) {

        try {
            return mapper.readValue(data, JsonNode.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }
}