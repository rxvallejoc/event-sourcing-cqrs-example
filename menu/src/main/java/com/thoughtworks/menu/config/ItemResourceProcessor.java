package com.thoughtworks.menu.config;

import com.thoughtworks.menu.command.api.MenuItemCommandsController;
import com.thoughtworks.menu.query.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ItemResourceProcessor implements ResourceProcessor<Resource<Item>> {

    @Autowired
    private RepositoryRestMvcConfiguration configuration;

    @Override
    public Resource<Item> process(Resource<Item> resource) {
        String id = resource.getContent().getId();
        resource.add(linkTo(methodOn(MenuItemCommandsController.class).getCommands(id)).withRel("commands"));
        return resource;
    }
}

