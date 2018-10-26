package ch.sebooom.jelastic.activemqproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consummer {

    @JmsListener(destination = "q.test", containerFactory = "myFactory")
    public void receiveMessage(TestMessage testMessage) {
        log.info("Message received: {}", testMessage);
    }
}
