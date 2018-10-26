package ch.sebooom.jelastic.activemqproducer.application.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;


@RestController
@RequestMapping("/send")
public class ProducerController {

    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping
    public void test() throws JMSException {

        jmsTemplate.convertAndSend("q.test","tutu");
    }
}
