package com.example.demo.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {



    @Bean
    public Exchange exchange(){return ExchangeBuilder.topicExchange("AccountEvent").build();}

    @Bean
    public Queue queue(){return QueueBuilder.durable("ComplaintEvents").build();}

    @Bean
    public Binding binding(){return BindingBuilder.bind(queue()).to(exchange()).with("com.example.demo.events.complaints.#").noargs();}



    @Bean
    public Queue DebitedAccountEventQueue(){return QueueBuilder.durable("DebitedAccountEvent").build();}

    @Bean
    public Binding DebitedAccountEventBinding(){return BindingBuilder.bind(DebitedAccountEventQueue()).to(exchange()).with("com.example.demo.events.DebitedAccount.#").noargs();}

    @Bean
    public Queue BankAccountSourceNotFoundEventQueue(){return QueueBuilder.durable("BankAccountSourceNotFoundEvent").build();}

    @Bean
    public Binding BankAccountSourceNotFoundEventBinding(){return BindingBuilder.bind(BankAccountSourceNotFoundEventQueue()).to(exchange()).with("com.example.demo.events.BankAccountSourceNotFound.#").noargs();}

    @Bean
    public Queue DebitedAccountFailedEventQueue(){return QueueBuilder.durable("DebitedAccountFailedEvent").build();}

    @Bean
    public Binding DebitedAccountFailedEventBinding(){return BindingBuilder.bind(DebitedAccountFailedEventQueue()).to(exchange()).with("com.example.demo.events.DebitedAccountFailed.#").noargs();}

    @Bean
    public Queue BankAccountDestinationNotFoundEventQueue(){return QueueBuilder.durable("BankAccountDestinationNotFoundEvent").build();}

    @Bean
    public Binding BankAccountDestinationNotFoundEventBinding(){return BindingBuilder.bind(BankAccountDestinationNotFoundEventQueue()).to(exchange()).with("com.example.demo.events.BankAccountSourceNotFound.#").noargs();}

    @Bean
    public Queue CreditedAccountEventQueue(){return QueueBuilder.durable("CreditedAccountEvent").build();}

    @Bean
    public Binding CreditedAccountEventBinding(){return BindingBuilder.bind(CreditedAccountEventQueue()).to(exchange()).with("com.example.demo.events.CreditedAccount.#").noargs();}



    @Bean
    public boolean configure(AmqpAdmin admin){

        admin.declareExchange(exchange());

        admin.declareQueue(queue());
        admin.declareBinding(binding());

        admin.declareQueue(DebitedAccountEventQueue());
        admin.declareBinding(DebitedAccountEventBinding());

        admin.declareQueue(BankAccountSourceNotFoundEventQueue());
        admin.declareBinding(BankAccountSourceNotFoundEventBinding());

        admin.declareQueue(DebitedAccountFailedEventQueue());
        admin.declareBinding(DebitedAccountFailedEventBinding());

        admin.declareQueue(BankAccountDestinationNotFoundEventQueue());
        admin.declareBinding(BankAccountDestinationNotFoundEventBinding());

        admin.declareQueue(CreditedAccountEventQueue());
        admin.declareBinding(CreditedAccountEventBinding());

        admin.declareQueue(queue());
        admin.declareBinding(binding());



        return true;
    }











}
