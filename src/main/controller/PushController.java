package main.controller;

import main.model.Post;
import main.util.JacksonDecoder;
import main.util.JacksonEncoder;
import org.atmosphere.annotation.Broadcast;
import org.atmosphere.config.service.*;
import org.atmosphere.cpr.*;
import org.atmosphere.jersey.Broadcastable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Context;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "users/posts/{id}")
@ManagedService(path = "/users/posts/{id}")
@Singleton
public class PushController {


    BroadcasterFactory factory = new DefaultBroadcasterFactory();

    private Broadcaster topic;

    @Ready
    @DeliverTo(DeliverTo.DELIVER_TO.BROADCASTER)
    public void onReady(AtmosphereResource resource) {
        System.out.println("ready");
//        resource.suspend();
        Integer id = new Integer(1);
//        topic = factory.get();
//        (Broadcaster.class, id);

        topic = resource.getAtmosphereConfig().getBroadcasterFactory().lookup(1, true);
        topic.addAtmosphereResource(resource);

        if (topic == null)
            System.out.println("null broadcaster");


    }

    @Broadcast(value = "/users/posts/1")
    @Message(encoders = {JacksonEncoder.class}, decoders = {JacksonDecoder.class})
    public Post onMessage(Post post) throws ExecutionException, InterruptedException {
        System.out.println("topic" + post.getMessage());
         java.util.concurrent.Future<Object> future = topic.broadcast(post);
//        future.get();

//        resource.suspend();
//        return "tops";
//        return new SuspendResponse.SuspendResponseBuilder<String>().broadcaster(topic).outputComments(true).build();
        return post;
    }

    @Disconnect
    public void disconnected(AtmosphereResourceEvent r) {
        System.out.println("dis connect");
    }

    public Broadcastable put(@Context final BroadcasterFactory factory, Post post) {
        System.out.println("sd");
            return  new Broadcastable(factory.lookup(post.getId(),true));
    }
}
