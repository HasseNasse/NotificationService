package net.hassannazar.general;

import net.hassannazar.notification.controller.NotificationEmitter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class ApplicationStartup {

    @Inject
    NotificationEmitter notificationEmitter;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        System.out.println("Starting app");
        //Start producing emissions at application startup
        notificationEmitter.generateNotifiations();
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {

    }
}
