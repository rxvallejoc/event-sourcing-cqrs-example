package com.thoughtworks.menu.query.listeners;

import com.thoughtworks.menu.events.MenuItemAddedEvent;
import com.thoughtworks.menu.query.Item;
import com.thoughtworks.menu.query.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuItemAddedEventListener {

    @Autowired
    ItemRepository items;

    @EventHandler
    public void on(MenuItemAddedEvent event) {
        items.save(new Item(event.getId(), event.getDescription(), event.getPrice()));
    }
}
