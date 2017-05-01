package com.thoughtworks.menu.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuItemModifiedEvent {
    String id;
    String description;
    BigDecimal price;
}
