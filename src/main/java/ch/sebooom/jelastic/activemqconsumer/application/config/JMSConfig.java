package ch.sebooom.jelastic.activemqconsumer.application.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;

@Slf4j
@Configuration
public class JMSConfig {

    //en dev configuration via le fichier de properties
    @Value("${spring.activemq.broker-url}")
    private String JMS_BROKER_URI_PROPS;

    //jelastic configuration via variable d'env
    @Value("#{systemProperties['broker.uri']}")
    private String JMS_BROKER_URI_ENV;

    @Value("${app.activemq.test.queue.name}")
    private String TEST_QUEUE_NAME;

    // Only required due to defining consumerFactory in the receiver
    @Bean
    public JmsListenerContainerFactory<?> consumerFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        // anonymous class
        factory.setErrorHandler(
                new ErrorHandler() {
                    @Override
                    public void handleError(Throwable t) {
                        log.error("Error jms handler: {}", t.getMessage());
                    }
                });

        // lambda function
        factory.setErrorHandler(t -> log.error("Error jms handler: {}", t.getMessage()));

        configurer.configure(factory, connectionFactory());
        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        log.info("JMS Broker uri from properties: {}", JMS_BROKER_URI_PROPS);
        log.info("JMS Broker uri from environnement: {}", JMS_BROKER_URI_ENV);

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        if(null!= JMS_BROKER_URI_ENV){
            log.info("JMS Broker uri from env used...");
            connectionFactory.setBrokerURL(JMS_BROKER_URI_ENV);
        }else{
            log.info("JMS Broker uri from properties used...");
            connectionFactory.setBrokerURL(JMS_BROKER_URI_PROPS);
        }

        return connectionFactory;
    }

    // Serialize message content to json using TextMessage
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
