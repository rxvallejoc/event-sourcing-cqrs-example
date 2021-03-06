package com.thoughtworks.menu.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ModifyMenuItemCommand {
    @TargetAggregateIdentifier
    String id;
    String description;
    BigDecimal price;
}

