package com.thoughtworks;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	public Exchange exchange() {
		return ExchangeBuilder.fanoutExchange("CustomerEvents").build();
	}

	@Bean
	public Queue queue() {
		return QueueBuilder.durable("CustomerEvents").build();
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
	}

	@Autowired
	public void configure(AmqpAdmin admin) {
		admin.declareExchange(exchange());
		admin.declareQueue(queue());
		admin.declareBinding(binding());
	}

}
