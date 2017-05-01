package com.thoughtworks.customers.query.listeners;


import com.thoughtworks.customers.events.CustomerSignedUpEvent;
import com.thoughtworks.customers.query.Customer;
import com.thoughtworks.customers.query.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CustomerSignedUpEventListener {

    @Autowired
    CustomerRepository customers;

    @EventHandler
    public void on(CustomerSignedUpEvent event) {
        customers.save(new Customer(event.getId(), event.getEmail()));
    }
}
