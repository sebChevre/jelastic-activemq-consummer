package ch.sebooom.jelastic.activemqconsumer;

import ch.sebooom.jelastic.activemqproducer.infrastructure.jms.test.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consummer {

    @JmsListener(destination = "${app.activemq.test.queue.name}", containerFactory = "consumerFactory")
    public void receiveMessage(TestMessage testMessage) {
        log.info("Message received: {}", testMessage);
    }
}
