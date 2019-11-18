package net.hassannazar.general;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationStartup {

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        System.out.println("Starting app");
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {

    }
}
