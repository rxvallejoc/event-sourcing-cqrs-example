package com.thoughtworks.orders.query.listeners;

import com.thoughtworks.menu.events.MenuItemAddedEvent;
import com.thoughtworks.orders.query.MenuItem;
import com.thoughtworks.orders.query.MenuItemRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Logger;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Component
@ProcessingGroup("amqpEvents")
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuItemAddedEventListener {

    @Autowired
    MenuItemRepository menu;

    @EventHandler
    public void on(MenuItemAddedEvent event) {
        Logger
            .getInstance(getClass())
            .info(format("Handling event: %s", event));

        menu.save(new MenuItem(event.getId(), event.getPrice()));
    }
}
