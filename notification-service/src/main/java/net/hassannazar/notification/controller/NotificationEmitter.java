package net.hassannazar.notification.controller;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationEmitter {

    @Incoming("incoming-notifications")
    @Outgoing("generated-notifications")
    public PublisherBuilder<String> generateNotifiations(String notification){
        return ReactiveStreams.of(notification)
                .map(String::toUpperCase);
    }
    
}
