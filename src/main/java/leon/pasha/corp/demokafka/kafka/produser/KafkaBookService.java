package leon.pasha.corp.demokafka.kafka.produser;

import leon.pasha.corp.demokafka.dto.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaBookService {

    @Autowired
    KafkaProducer kafkaBookProducer;

    @Value("${app.kafka.topic}")
    private String topic;

    public void bookSend(Book book){
        log.info("simple sending book='{}' to topic='{}'", book, topic);
        try {
            ProducerRecord<String, Book> record = new ProducerRecord<>(topic, book);
            //ProducerRecord<String, String> record = new ProducerRecord<>(topic, "book_kafka");
            kafkaBookProducer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
