package leon.pasha.corp.demokafka;

import leon.pasha.corp.demokafka.kafka.produser.KafkaSimpleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoKafkaApplication.class)
public class KafkaSimpleServiceTests {
	@Autowired
    KafkaSimpleService kafkaSimpleService;

	static int randomInt = (int)(Math.random() * 50 + 1);

	int getRandomInt() {
		return randomInt;
	}

	String prefixTopic(){
		return "Hello from KafkaSimpleServiceTests " + getRandomInt() + " ";
	}

	@Test
	public void testSimpleSend() {
		kafkaSimpleService.simpleSend(prefixTopic() + "simpleSend");
	}

	@Test
	public void testSendWithKey() {
		kafkaSimpleService.sendWithKey("Hi", prefixTopic() + "with key hi");
	}

	@Test
	public void testAsynchronousSend() {
		kafkaSimpleService.asynchronousSend(prefixTopic() + "with asynchronous");
	}

	@Test
	public void testSynchronousSend() {
		kafkaSimpleService.synchronousSend(prefixTopic() + "with synchronous send");
	}

}
