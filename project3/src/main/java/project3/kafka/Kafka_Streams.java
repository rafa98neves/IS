package project3.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import project3.Serdes.JsonSaleDeserializer;
import project3.Serdes.JsonSaleSerializer;
import project3.data.Sale;


public class Kafka_Streams {

    public static void main(String[] args) throws InterruptedException, IOException {

        String salesT = "sales_topic";
        String purchasesT = "purchases_topic";
        String outtopicname = "resultstopic";

        Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<Sale> SaleSerializer = new JsonSaleSerializer<>();
        serdeProps.put("JsonSaleClass", Sale.class);
        SaleSerializer.configure(serdeProps, false);

        final Deserializer<Sale> SaleDeserializer = new JsonSaleDeserializer<>();
        serdeProps.put("JsonSaleClass", Sale.class);
        SaleDeserializer.configure(serdeProps, false);

        final Serde<Sale> saleSerde = Serdes.serdeFrom(SaleSerializer, SaleDeserializer);

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder sales_builder = new StreamsBuilder();
        KStream<String, Sale> sales_stream = sales_builder.stream(salesT, Consumed.with(Serdes.String(), saleSerde));

        //StreamsBuilder purchases_builder = new StreamsBuilder();
        //KStream<String, Sale> purchases_stream = purchases_builder.stream(purchasesT, Consumed.with(Serdes.String(), sale_serde));


        // Revenue per item

        KTable<String, Long> outstream = sales_stream.mapValues(v -> v.getPrice()).groupByKey().count();
        outstream.mapValues((k, v) -> k + " revenue = " + v).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));

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

        //System.out.println("Reading stream from topic " + salesT);
        //System.out.println("Reading stream from topic " + purchasesT);
    }
}