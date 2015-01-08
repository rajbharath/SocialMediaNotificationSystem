package main.controller;

import main.model.Post;
import main.util.JacksonDecoder;
import main.util.JacksonEncoder;
import org.atmosphere.annotation.Broadcast;
import org.atmosphere.config.service.*;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.jersey.Broadcastable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Context;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "users/posts/{id}")
@ManagedService(path = "/users/posts/{id}")
//@Singleton
//@AtmosphereService(broadcaster = Broadcaster.class)
public class PushController {


    private Broadcaster topic;

    @Ready
    @DeliverTo(DeliverTo.DELIVER_TO.BROADCASTER)
    public void onReady(AtmosphereResource resource) {
        System.out.println("ready"+ resource.uuid());
        topic = resource.getAtmosphereConfig().getBroadcasterFactory().lookup(resource.uuid(), true);
        topic.addAtmosphereResource(resource);
        if (topic == null)
            System.out.println("null broadcaster");


    }

    @Message(encoders = {JacksonEncoder.class}, decoders = {JacksonDecoder.class})
    public Post onMessage(Post post) throws ExecutionException, InterruptedException {
        System.out.println("topic" + post.getMessage());
        return post;
    }

    @Disconnect
    public void disconnected(AtmosphereResourceEvent r) {
        System.out.println("dis connect");
    }

    @Broadcast
    public Broadcastable put(@Context final BroadcasterFactory factory, Post post) {
        System.out.println("sd");
        return new Broadcastable(factory.lookup(post.getId(), true));
    }

}
