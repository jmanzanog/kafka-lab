package io.jm.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

public class ConsumerDemoWithShutdown {
    private static final Logger log = LoggerFactory.getLogger(ConsumerDemoWithShutdown.class);

    public static void main(String[] args) {
        log.info("Hello kafka Consumer");

        // configure kafka producer properties
        AtomicReference<Properties> properties = new AtomicReference<>(new Properties());
        properties.get().setProperty(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.get().setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.get().setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.get().setProperty(GROUP_ID_CONFIG, "consumer-group1");
        properties.get().setProperty(AUTO_OFFSET_RESET_CONFIG, "earliest");

        // instance a consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties.get());

        // Get reference to the current thread
        final Thread mainThread = Thread.currentThread();

        // adding the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("detect a shutdown, let's exit by calling consumer.wakeup()");
            consumer.wakeup();

            // join the main thread to allow execution of the code in the main thread

            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }));

        try {

            // subscribe consumer to topic
            consumer.subscribe(Collections.singletonList("Demo_Java_Topic"));

            // poll for new data

            while (true) {
                log.info("Pooling...");
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    log.info("Key: " + record.key() + ", value: " + record.value());
                    log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                }
            }
        } catch (WakeupException e) {
            log.info("expected exception", e);
        } catch (Exception e) {
            log.error("unexpected exception", e);
        } finally {
            consumer.close(); // this will also commit the offsets if need be
        }


    }
}
