package com.thoughtworks.orders.config;

import com.thoughtworks.orders.command.api.OrderCommandsController;
import com.thoughtworks.orders.query.Order;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OrderResourceProcessor implements ResourceProcessor<Resource<Order>> {

    @Override
    public Resource<Order> process(Resource<Order> resource) {
        String id = resource.getContent().getId();
        resource.add(linkTo(methodOn(OrderCommandsController.class).getCommands(id)).withRel("commands"));
        return resource;
    }
}

