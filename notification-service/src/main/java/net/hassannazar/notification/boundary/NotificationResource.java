package net.hassannazar.notification.boundary;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Channel;
import org.reactivestreams.Publisher;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@Path("/notifications")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {

    @Context
    Sse sse;

    @Inject
    @Channel("generated-notifications-internal")
    Publisher<String> notifications;

    private SseBroadcaster sseBroadcaster;

    @PostConstruct
    public void setupResource() {
        sseBroadcaster = sse.newBroadcaster();

        OutboundSseEvent.Builder eventBuilder = sse.newEventBuilder()
                .mediaType(MediaType.APPLICATION_JSON_TYPE);

        Flowable.fromPublisher(notifications)
                .doOnNext(data -> {
                    System.out.println("Sending event: " + data);
                })
                .map(data -> {
                    return eventBuilder
                            .data(data)
                            .build();
                })
                .doOnError(e -> {
                    System.err.println(e.getMessage());
                })
                .doFinally(sseBroadcaster::close)
                .subscribe(sseBroadcaster::broadcast);
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void publishNotifications(@Context SseEventSink sseEventSink) {
        sseBroadcaster.register(sseEventSink);
    }

}
