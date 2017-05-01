package com.thoughtworks.orders.command.api;

import com.thoughtworks.orders.command.AddDeliveryAddressToOrderCommand;
import com.thoughtworks.orders.command.CancelOrderCommand;
import com.thoughtworks.orders.command.PlaceOrderCommand;
import com.thoughtworks.orders.command.api.Exceptions.AddressNotFoundException;
import com.thoughtworks.orders.config.ResourceURI;
import com.thoughtworks.orders.query.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.callbacks.FutureCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders/{id}/commands")
@ResponseStatus(HttpStatus.ACCEPTED)
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrderCommandsController implements ResourceProcessor<RepositoryLinksResource> {

    @Autowired
    CommandGateway commander;

    @Autowired
    AddressRepository addresses;

    @PostMapping("/add-delivery-address")
    public Future<?> addDeliveryAddress(@PathVariable String id, @RequestBody Map<String, String> body) {
        String addressId = new ResourceURI(body.get("address")).getId();

        Optional
            .ofNullable(addresses.findOne(addressId))
            .orElseThrow(AddressNotFoundException::new);

        FutureCallback<AddDeliveryAddressToOrderCommand, Object> callback = new FutureCallback<>();
        commander.send(new AddDeliveryAddressToOrderCommand(id, addressId), callback);
        return callback.toCompletableFuture();
    }

    @PostMapping("/cancel")
    public Future<?> cancel(@PathVariable String id) {
        FutureCallback<CancelOrderCommand, Object> callback = new FutureCallback<>();
        commander.send(new CancelOrderCommand(id), callback);
        return callback.toCompletableFuture();
    }

    @PostMapping("/place")
    public Future<?> place(@PathVariable String id) {
        FutureCallback<PlaceOrderCommand, Object> callback = new FutureCallback<>();
        commander.send(new PlaceOrderCommand(id), callback);
        return callback.toCompletableFuture();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResourceSupport> getCommands(@PathVariable String id) {
        ResourceSupport commands = new ResourceSupport();

        commands.add(linkTo(methodOn(getClass()).addDeliveryAddress(id, null)).withRel("add-delivery-address").expand(id));
        commands.add(linkTo(methodOn(getClass()).cancel(id)).withRel("cancel").expand(id));
        commands.add(linkTo(methodOn(getClass()).place(id)).withRel("place").expand(id));

        return ResponseEntity.ok(commands);
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        return resource;
    }
}
