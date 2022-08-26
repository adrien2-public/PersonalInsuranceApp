package com.example.demo.config;


import com.rabbitmq.client.Channel;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public SpringAMQPMessageSource complaintEvents( AMQPMessageConverter messageConverter) {
        return new SpringAMQPMessageSource(messageConverter) {

            @RabbitListener( queues = "DebitedAccountEvent")
            @Override
            public void onMessage( Message message,  Channel channel) {
                System.out.println("DebitedAccountEvent received external message:" + message + " channel: " + channel);
                super.onMessage(message, channel);
            }

            @RabbitListener( queues = "BankAccountSourceNotFoundEvent")
            public void sourceNotFound( Message message,  Channel channel) {
                System.out.println("BankAccountSourceNotFoundEvent received external message:" + message + " channel: " + channel);
                super.onMessage(message, channel);
            }

            @RabbitListener(queues = "DebitedAccountFailedEvent")
            public void DebitedAccountFailed( Message message,  Channel channel) {
                System.out.println("DebitedAccountFailedEvent received external message:" + message + " channel: " + channel);
                super.onMessage(message, channel);
            }

            @RabbitListener( queues = "BankAccountDestinationNotFoundEvent")
            public void BankAccountDestinationNotFound( Message message,  Channel channel) {
                System.out.println("BankAccountDestinationNotFoundEvent received external message:" + message + " channel: " + channel);
                super.onMessage(message, channel);
            }

            @RabbitListener(queues = "CreditedAccountEvent")
            public void CreditedAccount( Message message,  Channel channel) {
                System.out.println("CreditedAccountEvent received external message:" + message + " channel: " + channel);
                super.onMessage(message, channel);
            }

        };
    }

}
