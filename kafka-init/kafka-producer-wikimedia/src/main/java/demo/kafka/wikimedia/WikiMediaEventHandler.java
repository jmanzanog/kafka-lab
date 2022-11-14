package demo.kafka.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WikiMediaEventHandler implements EventHandler {
    private final String topic;
    private final KafkaProducer<String, String> producer;

    private final Logger log = LoggerFactory.getLogger(WikiMediaEventHandler.class.getSimpleName());

    public WikiMediaEventHandler(KafkaProducer<String, String> producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {
        log.info("onOpen....");
    }

    @Override
    public void onClosed() throws Exception {
        producer.close();
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) {
        log.info(event);
        log.info(messageEvent.getData());
        producer.send(new ProducerRecord<>(this.topic, messageEvent.getData()));
    }

    @Override
    public void onComment(String comment) {

    }

    @Override
    public void onError(Throwable t) {
        log.error("Error event handler", t);
    }
}
