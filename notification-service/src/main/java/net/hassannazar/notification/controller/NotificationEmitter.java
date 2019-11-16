package net.hassannazar.notification.controller;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class NotificationEmitter {

    @Outgoing("generated-notifications-internal")
    public Flowable<String> generateNotifiations(){
        return Flowable.interval(5, TimeUnit.SECONDS)
                .map(tick -> {
                    return "Notification number: " + tick;
                });
    }
}
