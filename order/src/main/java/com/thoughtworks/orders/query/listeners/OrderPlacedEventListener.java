package com.thoughtworks.orders.query.listeners;

import com.thoughtworks.orders.events.OrderPlacedEvent;
import com.thoughtworks.orders.query.Order;
import com.thoughtworks.orders.query.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Logger;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Component
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrderPlacedEventListener {

    @Autowired
    OrderRepository orders;

    @EventHandler
    public void on(OrderPlacedEvent event) {
        Logger
            .getInstance(getClass())
            .info(format("Handling event: %s", event));

        Optional
            .ofNullable(orders.findOne(event.getOrderId()))
            .map(Order::place)
            .ifPresent(orders::save);
    }
}
