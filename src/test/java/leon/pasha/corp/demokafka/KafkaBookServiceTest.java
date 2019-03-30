package leon.pasha.corp.demokafka;

import leon.pasha.corp.demokafka.dto.Book;
import leon.pasha.corp.demokafka.kafka.produser.KafkaBookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoKafkaApplication.class)
public class KafkaBookServiceTest {
    @Autowired
    KafkaBookService kafkaBookService;

    static int randomInt = (int)(Math.random() * 50 + 1);

    int getRandomInt() {
        return randomInt;
    }

    @Test
    public void testBookSend() {
        Book book = new Book(1, "kafka");
        kafkaBookService.bookSend(book);
    }
}
