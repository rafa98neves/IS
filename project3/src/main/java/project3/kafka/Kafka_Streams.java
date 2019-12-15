package project3.kafka;

import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.protocol.types.Field;
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

import javax.validation.constraints.Null;


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
        String outtopicname[] = {"resultstopic1", "resultstopic2", "resultstopic3"};

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

        long windowSize = TimeUnit.MINUTES.toMillis(1);


        props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Float().getClass());


        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Sale> sales_stream = builder.stream(salesT, Consumed.with(Serdes.String(), saleSerde));
        KStream<String, Purchase> purchases_stream = builder.stream(purchasesT, Consumed.with(Serdes.String(), purchaseSerde));


        String itemTableJSON = "{\"schema\": {\"type\":\"struct\", \"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"item_id\"}, {\"type\":\"float\",\"optional\":false,\"field\":\"revenues\"}," +
                "{\"type\":\"float\",\"optional\":false,\"field\":\"expenses\"},{\"type\":\"float\",\"optional\":false,\"field\":\"profit\"}, {\"type\":\"float\",\"optional\":false,\"field\":\"average_purchase\"}]," +
                " \"optional\": false, \"name\": \"item_table\"}, \"payload\":{";

        String totalTableJSON = "{\"schema\": {\"type\":\"struct\", \"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"id\"}, {\"type\":\"float\",\"optional\":false,\"field\":\"revenues\"}," +
                "{\"type\":\"float\",\"optional\":false,\"field\":\"expenses\"},{\"type\":\"float\",\"optional\":false,\"field\":\"profit\"}, {\"type\":\"float\",\"optional\":false,\"field\":\"average_purchase\"}]," +
                " \"optional\": false, \"name\": \"total_table\"}, \"payload\":{";

        String windowedTableJSON = "{\"schema\": {\"type\":\"struct\", \"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"id\"}, {\"type\":\"float\",\"optional\":false,\"field\":\"revenues\"}," +
                "{\"type\":\"float\",\"optional\":false,\"field\":\"expenses\"},{\"type\":\"float\",\"optional\":false,\"field\":\"profit\"}]," +
                " \"optional\": false, \"name\": \"windowed_table\"}, \"payload\":{";

        // Revenue per item

        KTable<Integer, Float> revenueTable = sales_stream.map((key, value) -> KeyValue.pair(value.getItem().getItem_id(), value.getPrice())).groupByKey().reduce((a,b) -> a + b);
        KStream<Integer, String> itemStream = revenueTable.toStream().map((key, value) -> KeyValue.pair(key, "\"item_id\" :" + key + ", \"revenues\":" + value));

        // Expenses per item

        KTable<Integer, Float> expensesTable = purchases_stream.map((k,v) -> KeyValue.pair(v.getItem().getItem_id(), v.getPrice())).groupByKey().reduce((a, b) -> a + b);
        itemStream = itemStream.leftJoin(expensesTable, (left, right) -> left + ", \"expenses\": " + right, Joined.with(Serdes.Integer(),Serdes.String(), Serdes.Float()));

        // Profit per item

        KTable<Integer, Float> profitTable = revenueTable.leftJoin(expensesTable, (revenues, expenses) -> revenues - expenses);
        itemStream = itemStream.leftJoin(profitTable, (left, right) -> left + ", \"profit\": " + right, Joined.with(Serdes.Integer(),Serdes.String(), Serdes.Float()));

        // Total revenues

        KTable<Integer, Float> totalRevenueTable = sales_stream.map((k,v) -> KeyValue.pair(1, v.getPrice())).groupByKey().reduce((a, b) -> a + b);
        KStream<Integer, String> totalStream = totalRevenueTable.toStream().map((key, value) -> KeyValue.pair(key, "\"id\": 1, \"revenues\":" + value));

        // Total expenses

        KTable<Integer, Float> totalExpensesTable = purchases_stream.map((k,v) -> KeyValue.pair(1, v.getPrice())).groupByKey().reduce((a, b) -> a + b);
        totalStream = totalStream.leftJoin(totalExpensesTable, (left, right) -> left + ", \"expenses\": " + right, Joined.with(Serdes.Integer(), Serdes.String(), Serdes.Float()));
        // Total profit

        KTable<Integer, Float> totalProfitTable = totalRevenueTable.leftJoin(totalExpensesTable, (totalRevenues, totalExpenses) -> totalRevenues - totalExpenses);
        totalStream = totalStream.leftJoin(totalProfitTable, (left, right) -> left + ", \"profit\": " + right, Joined.with(Serdes.Integer(), Serdes.String(), Serdes.Float()));

        // Average amount spent in each purchase (separated by item)
        KTable<Integer, Long> countByItem = purchases_stream.map((k,v) -> KeyValue.pair(v.getItem().getItem_id(), v.getPrice())).groupByKey().count();
        KTable<Integer, Float> averagePurchaseByItem = expensesTable.leftJoin(countByItem, (expenses, number) -> expenses/number.floatValue());

        itemStream = itemStream.leftJoin(averagePurchaseByItem, (left, right) -> itemTableJSON + left + ", \"average_purchase\": " + right + "}}", Joined.with(Serdes.Integer(),Serdes.String(), Serdes.Float()));
        itemStream.to(outtopicname[0], Produced.with(Serdes.Integer(), Serdes.String()));

        // Average amount spent in each purchase (aggregated for all items)

        KTable<Integer, Long> count = purchases_stream.map((k, v) -> KeyValue.pair(1, v.getPrice())).groupByKey().count();
        KTable<Integer, Float> averagePurchase = totalExpensesTable.leftJoin(count, (expenses, n) -> expenses / n.floatValue());

        totalStream = totalStream.leftJoin(averagePurchase, (left, right) -> totalTableJSON + left + ", \"average_purchase\": " + right + "}}", Joined.with(Serdes.Integer(), Serdes.String(), Serdes.Float()));
        totalStream.to(outtopicname[1], Produced.with(Serdes.Integer(), Serdes.String()));

        // Highest profit of all (only one if there is a tie)

        // Total revenue in the last hour (use a tumbling time window)

       // KTable<Windowed<Integer>, Float> totalRevenueTableTumbling = sales_stream.map((k, v) -> KeyValue.pair(0, v.getPrice())).groupByKey().windowedBy(TimeWindows.of(windowSize)).reduce((a, b) -> a + b);
       // KStream windowedStream = totalRevenueTableTumbling.toStream().map((key, value) -> KeyValue.pair(key, "\"id\": 1, \"revenues\":" + value));
        // Total expenses in the last hour (use a tumbling time window)

        //KTable<Windowed<Integer>, Float> totalExpensesTableTumbling = purchases_stream.map((k, v) -> KeyValue.pair(0, v.getPrice())).groupByKey().windowedBy(TimeWindows.of(windowSize)).reduce((a, b) -> a + b);
     //   windowedStream = windowedStream.leftJoin(totalExpensesTableTumbling, (left, right) -> left + ", \"expenses\":" + right, Joined.with(Serdes.Integer(), Serdes.String(), Serdes.Float()));
        // Total profits in the last hour (use a tumbling time window)

      //  KTable<Windowed<Integer>, Float> totalProfitTableTumbling = totalRevenueTableTumbling.leftJoin(totalExpensesTableTumbling, (revenues, expenses) -> revenues - expenses);
      //  windowedStream = windowedStream.leftJoin(totalProfitTableTumbling, (left, right) -> windowedTableJSON + left + ", \"profit\":" + right + "}}", Joined.with(Serdes.Integer(), Serdes.String(), Serdes.Float()));
      //  windowedStream.to(outtopicname[2], Produced.with(WindowedSerdes.timeWindowedSerdeFrom(Integer.class), Serdes.String()));

        // Name of the country with the highest sales per item. Include the value of such sales

        KafkaStreams revenue_streams = new KafkaStreams(builder.build(), props);
        revenue_streams.start();
        KafkaStreams purchase_streams = new KafkaStreams(builder.build(), props);
        purchase_streams.start();

    }
}
