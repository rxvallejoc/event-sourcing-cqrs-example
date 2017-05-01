package com.thoughtworks.menu.query.listeners;

import com.thoughtworks.menu.query.Item;
import com.thoughtworks.menu.query.ItemRepository;
import com.thoughtworks.orders.events.ItemAddedToOrderEvent;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Logger;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Component
@ProcessingGroup("amqpEvents")
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ItemAddedToOrderEventListener {

    @Autowired
    ItemRepository items;

    @EventHandler
    public void on(ItemAddedToOrderEvent event) {
        Logger
            .getInstance(getClass())
            .info(format("Handling event: %s", event));

        Optional
            .ofNullable(items.findOne(event.getMenuItemId()))
            .map(Item::increasePopularity)
            .ifPresent(items::save);
    }
}
