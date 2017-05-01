package com.thoughtworks.orders.query.listeners;

import com.thoughtworks.orders.events.ItemAddedToOrderEvent;
import com.thoughtworks.orders.query.MenuItemRepository;
import com.thoughtworks.orders.query.OrderItem;
import com.thoughtworks.orders.query.OrderItemRepository;
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
public class ItemAddedToOrderEventListener {

    @Autowired
    OrderRepository orders;

    @Autowired
    MenuItemRepository menuItems;

    @Autowired
    OrderItemRepository orderItems;

    @EventHandler
    public void on(ItemAddedToOrderEvent event) {
        Logger
            .getInstance(getClass())
            .info(format("Handling event: %s", event));

        Optional.ofNullable(orders.findOne(event.getOrderId()))
            .ifPresent(order -> Optional.ofNullable(menuItems.findOne(event.getMenuItemId()))
                .map(menuItem -> new OrderItem(event.getOrderItemId(), order, menuItem, event.getQuantity()))
                .ifPresent(orderItems::save));
    }
}
