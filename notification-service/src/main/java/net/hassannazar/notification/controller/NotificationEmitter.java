package net.hassannazar.notification.controller;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class NotificationEmitter {

    @Outgoing("generated-notifications")
    public Flowable<String> generateNotifiations(){
        return Flowable.interval(5, TimeUnit.SECONDS)
                .map(tick -> "Notification number: " + tick);
    }
    
    @Incoming("generated-notifications")
    public void incoming(String msg){
        System.out.println("msg = " + msg);
    }
    
}
