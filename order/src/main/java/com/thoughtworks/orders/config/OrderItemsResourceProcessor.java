package com.thoughtworks.orders.config;

import com.thoughtworks.orders.command.api.OrderItemsCommandsController;
import com.thoughtworks.orders.query.OrderItem;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OrderItemsResourceProcessor implements ResourceProcessor<Resources<OrderItem>> {

    @Override
    public Resources<OrderItem> process(Resources<OrderItem> resources) {
        String orderId = new ResourceURI(resources.getId().getHref()).getParentId();

        try {
            UUID.fromString(orderId);
            resources.add(linkTo(methodOn(OrderItemsCommandsController.class).getCommands(orderId))
                .withRel("commands").expand(orderId));
        } catch (Exception e) {}

        return resources;
    }
}

