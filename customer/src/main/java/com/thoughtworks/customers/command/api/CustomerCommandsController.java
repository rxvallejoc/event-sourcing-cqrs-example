package com.thoughtworks.customers.command.api;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/customers/{id}/commands")
@ResponseStatus(HttpStatus.ACCEPTED)
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CustomerCommandsController implements ResourceProcessor<RepositoryLinksResource> {

    @Autowired
    CommandGateway commander;

    @PostMapping("/change-email")
    public Future<?> changeEmail(@PathVariable String id, @RequestBody Map<String, String> body) {
        return new CompletableFuture<String>();
    }

    @PostMapping("/verify")
    public Future<?> verify(@PathVariable String id, @RequestBody Map<String, String> body) {
        return new CompletableFuture<String>();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResourceSupport> getCommands(@PathVariable String id) {
        ResourceSupport commands = new ResourceSupport();

        commands.add(linkTo(methodOn(getClass()).changeEmail(id, null)).withRel("change-email").expand(id));
        commands.add(linkTo(methodOn(getClass()).verify(id, null)).withRel("verify").expand(id));

        return ResponseEntity.ok(commands);
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        return resource;
    }
}
