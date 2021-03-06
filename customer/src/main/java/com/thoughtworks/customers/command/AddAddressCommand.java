package com.thoughtworks.customers.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AddAddressCommand {
    @TargetAggregateIdentifier
    String customerId;
    String addressId;
    String nickName;
    String location;
}
