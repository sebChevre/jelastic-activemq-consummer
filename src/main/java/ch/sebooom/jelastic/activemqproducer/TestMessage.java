package ch.sebooom.jelastic.activemqproducer;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

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
