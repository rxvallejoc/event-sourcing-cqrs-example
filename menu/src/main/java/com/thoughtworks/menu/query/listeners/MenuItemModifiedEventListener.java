package com.thoughtworks.menu.query.listeners;

import com.thoughtworks.menu.events.MenuItemModifiedEvent;
import com.thoughtworks.menu.query.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Component
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuItemModifiedEventListener {

    @Autowired
    ItemRepository items;

    @EventHandler
    public void on(MenuItemModifiedEvent event) {
        Optional
            .ofNullable(items.findOne(event.getId()))
            .map(item -> item.withDescription(event.getDescription()).withPrice(event.getPrice()))
            .ifPresent(items::save);
    }
}
