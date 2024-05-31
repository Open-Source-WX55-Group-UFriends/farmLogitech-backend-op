package com.farmlogitech.farmlogitechbackend.subscription.interfaces;


import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.FarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.FarmResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.queries.GetAllSubscriptionQuery;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.farmlogitech.farmlogitechbackend.subscription.domain.services.SubscriptionCommandService;
import com.farmlogitech.farmlogitechbackend.subscription.domain.services.SubscriptionQueryService;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.resources.CreateSubscriptionResource;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.resources.SubscriptionResource;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.transform.SubscriptionResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="/api/v1/subscription", produces = MediaType.APPLICATION_JSON_VALUE)

public class SubscriptionController {
    private final SubscriptionQueryService subscriptionQueryService;
    private final SubscriptionCommandService subscriptionCommandService;

    public SubscriptionController(SubscriptionQueryService subscriptionQueryService,
                                  SubscriptionCommandService subscriptionCommandService){
        this.subscriptionQueryService = subscriptionQueryService;
        this.subscriptionCommandService = subscriptionCommandService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource resource){
        Optional<Subscription> subscription = subscriptionCommandService.handle(CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource));
        return subscription.map(resp ->
                new ResponseEntity<>(SubscriptionResourceFromEntityAssembler
                        .toResourceFromEntity(resp), CREATED))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubscriptionResource>> getAllSubscription() {
        var subscriptions = subscriptionQueryService.handle(new GetAllSubscriptionQuery());
        if(subscriptions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var subscriptionResources = subscriptions.stream().map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(subscriptionResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResource>getSubscriptionById(@PathVariable int id){
        Optional<Subscription> subscription = subscriptionQueryService.handle(new GetSubscriptionByIdQuery(id));
        return subscription.map(resp->ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(resp)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
