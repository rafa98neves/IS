package project3.kafka;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import project3.data.Sale;


public class Kafka_Streams {

    public static void main(String[] args) throws InterruptedException, IOException {

        String salesT = "sales_topic";
        String purchasesT = "purchases_topic";
        String outtopicname = "resultstopic";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", JsonDeserializer.class);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", JsonSerializer.class);

        StreamsBuilder sales_builder = new StreamsBuilder();
        KStream<String, Sale> sales_stream = sales_builder.stream(salesT);

        StreamsBuilder purchases_builder = new StreamsBuilder();
        KStream<String, JsonNode> purchases_stream = purchases_builder.stream(purchasesT);

        // Revenue per item

        /*KTable<String, Integer> outstream = sales_stream.map(v -> v.getPrice()).groupByKey().reduce(0, (a,b) -> a + b);
        outstream.mapValues((k,v) -> k + " revenue = " + v.getPrice()).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));
        System.out.println(outstream.toString());*/

        // Expenses per item
        // Profit per item
        // Total revenues
        // Total expenses
        // Total profit
        // Average amount spent in each purchase (separated by item)
        // Average amount spent in each purchase (aggregated for all items)
        // Highest profit of all (only one if there is a tie)
        // Total revenue in the last hour 1 (use a tumbling time window)
        // Total expenses in the last hour (use a tumbling time window)
        // Total profits in the last hour (use a tumbling time window)
        // Name of the country with the highest sales per item. Include the value of such sales


        /*outstream = purchases_stream.groupByKey().count();
        outstream.mapValues((k,v) -> k + "=>" + v).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));
        */
        KafkaStreams streams = new KafkaStreams(sales_builder.build(), props);
        streams.start();

        System.out.println("Reading stream from topic " + salesT);
        System.out.println("Reading stream from topic " + purchasesT);
    }
}