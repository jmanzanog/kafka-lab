package io.jm.kafka;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

public class ProducerDemoWithCallback {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

    public static void main(String[] args) {
        log.info("Hello kafka, i am a producer");

        // configure kafka producer properties
        AtomicReference<Properties> properties = new AtomicReference<>(new Properties());
        properties.get().setProperty(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.get().setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.get().setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // instance a producer and a record  to sent to kafka
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties.get())) {

            for (int i = 0; i < 10; i++) {

                ProducerRecord<String, String> record = new ProducerRecord<>("Demo_Java_Topic", "Key00" + i, "Value00" + i);

                // send async
                producer.send(record, (metadata, exception) -> {
                    // execute every time a record is successfully sent or an exception is thrown
                    if (exception != null) {
                        log.error("Fail to sent record", exception);
                    } else {
                        log.info("Received new metadata/ \n " +
                                "Topic: " + metadata.topic() + "\n " +
                                "Partition: " + metadata.partition() + "\n " +
                                "Offset: " + metadata.offset() + "\n " +
                                "Timestamp: " + metadata.timestamp());
                    }
                });
            }

            // wait to send all message
            producer.flush();
        }
        log.info("end  kafka");

    }
}
