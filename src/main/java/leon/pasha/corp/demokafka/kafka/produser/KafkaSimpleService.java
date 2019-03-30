package leon.pasha.corp.demokafka.kafka.produser;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaSimpleService {
    //@Autowired
    //private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    @Value("${app.kafka.topic}")
    private String topic;

    public void simpleSend(String message){
        log.info("simple sending message='{}' to topic='{}'", message, topic);
        try {
            //kafkaTemplate.send(topic, message);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
            kafkaProducer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void synchronousSend(String message){
        log.info("synchronous sending message='{}' to topic='{}'", message, topic);
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
            //kafkaTemplate.send(topic, message).get();
            kafkaProducer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void asynchronousSend(String message){
        log.info("asynchronous sending message='{}' to topic='{}'", message, topic);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        kafkaProducer.send(record, new ProducerCallback());
    }

    public void sendWithKey(String key, String message){
        log.info("sending message='{}' to topic='{}' with key='{}'", message, topic, key);
        //kafkaTemplate.send(topic, key, message);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
        kafkaProducer.send(record);
    }

    class ProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            log.info("callback message");
            if (e != null) {
                log.info("we have a problem");
                e.printStackTrace();
            }
        }
    }
}
