package com.thoughtworks.orders.query.listeners;

import com.thoughtworks.orders.events.DeliveryAddressAddedToOrderEvent;
import com.thoughtworks.orders.query.AddressRepository;
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
public class DeliveryAddressAddedToOrderEventListener {

    @Autowired
    OrderRepository orders;

    @Autowired
    AddressRepository addresses;

    @EventHandler
    public void on(DeliveryAddressAddedToOrderEvent event) {
        Logger
            .getInstance(getClass())
            .info(format("Handling event: %s", event));

        Optional.ofNullable(orders.findOne(event.getOrderId()))
            .ifPresent(order -> Optional.ofNullable(addresses.findOne(event.getAddressId()))
                .map(address -> order.withAddress(address))
                .ifPresent(orders::save));
    }
}
