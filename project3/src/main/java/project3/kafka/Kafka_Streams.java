package project3.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import project3.Serdes.JsonPurchaseDeserializer;
import project3.Serdes.JsonPurchaseSerializer;
import project3.Serdes.JsonSaleDeserializer;
import project3.Serdes.JsonSaleSerializer;
import project3.data.Purchase;
import project3.data.Sale;


public class Kafka_Streams {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException, IOException {

        Serde<Sale> saleSerde;
        Serde<Purchase> purchaseSerde;
        java.util.Properties props;
        Map<String, Object> saleProps = new HashMap<>();
        Map<String, Object> purchaseProps = new HashMap<>();
        String salesT = "sales_topic";
        String purchasesT = "purchases_topic";
        String outtopicname = "resultstopic";

        final Serializer<Sale> SaleSerializer = new JsonSaleSerializer<>();
        saleProps.put("JsonSaleClass", Sale.class);
        SaleSerializer.configure(saleProps, false);

        final Deserializer<Sale> SaleDeserializer = new JsonSaleDeserializer<>();
        saleProps.put("JsonSaleClass", Sale.class);
        SaleDeserializer.configure(saleProps, false);

        saleSerde = Serdes.serdeFrom(SaleSerializer, SaleDeserializer);

        final Serializer<Purchase> PurchaseSerializer = new JsonPurchaseSerializer<>();
        purchaseProps.put("JsonPurchaseOrdersClass", Purchase.class);
        PurchaseSerializer.configure(purchaseProps, false);

        final Deserializer<Purchase> PurchaseDeserializer = new JsonPurchaseDeserializer<>();
        purchaseProps.put("JsonPurchaseOrdersClass", Purchase.class);
        PurchaseDeserializer.configure(purchaseProps, false);

        purchaseSerde = Serdes.serdeFrom(PurchaseSerializer, PurchaseDeserializer);

        props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Float().getClass());


        StreamsBuilder sales_builder = new StreamsBuilder();
        KStream<String, Sale> sales_stream = sales_builder.stream(salesT, Consumed.with(Serdes.String(), saleSerde));

        StreamsBuilder purchases_builder = new StreamsBuilder();
        KStream<String, Purchase> purchases_stream = purchases_builder.stream(purchasesT, Consumed.with(Serdes.String(), purchaseSerde));


        // Revenue per item

        KStream<Integer, Float> revenueStream = sales_stream.map((key, value) -> KeyValue.pair(value.getItem().getItem_id(), value.getPrice()));
        KTable<Integer, Float> revenueTable = revenueStream.groupByKey().reduce((a,b) -> a + b);
        revenueTable.mapValues((k, v) -> k + "=>" + v).toStream().to(outtopicname, Produced.with(Serdes.Integer(), Serdes.String()));

        // Expenses per item

        KTable<Integer, Float> table = purchases_stream.map((k,v) -> KeyValue.pair(v.getItem().getItem_id(), v.getPrice())).groupByKey().reduce((a, b) -> a + b);
        table.mapValues((k, v) -> "Item:" + k + " expenses = " + v.toString()).toStream().to(outtopicname, Produced.with(Serdes.Integer(), Serdes.String()));


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

        KafkaStreams streams = new KafkaStreams(purchases_builder.build(), props);
        streams.start();

        //System.out.println("Reading stream from topic " + salesT);
        //System.out.println("Reading stream from topic " + purchasesT);
    }
}
