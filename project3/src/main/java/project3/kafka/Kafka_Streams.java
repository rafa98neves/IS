package project3.kafka;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;


public class Kafka_Streams {

    public static void main(String[] args) throws InterruptedException, IOException {

        String salesT = "sales_topic";
        String purchasesT = "purchases_topic";
        String outtopicname = "resultstopic";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

        StreamsBuilder sales_builder = new StreamsBuilder();
        KStream<String, JsonNode> sales_stream = sales_builder.stream(salesT);

        StreamsBuilder purchases_builder = new StreamsBuilder();
        KStream<String, JsonNode> purchases_stream = purchases_builder.stream(salesT);

        KTable<String, Long> outstream = sales_stream.groupByKey().count();
        outstream.mapValues((k,v) -> k + "=>" + v).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));

        outstream = purchases_stream.groupByKey().count();
        outstream.mapValues((k,v) -> k + "=>" + v).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(sales_builder.build(), props);
        streams.start();

        System.out.println("Reading stream from topic " + salesT);
        System.out.println("Reading stream from topic " + purchasesT);
    }
}