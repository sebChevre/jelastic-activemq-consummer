package ch.sebooom.jelastic.activemqproducer.infrastructure.jms.test;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TestMessage {
    private String message;
    private String dateEnvoi;

    public TestMessage(String message, String dateEnvoi) {
        this.message = message;
        this.dateEnvoi = dateEnvoi;
    }

    public TestMessage() {
    }


}
