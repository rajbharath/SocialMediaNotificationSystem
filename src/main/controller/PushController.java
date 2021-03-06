package main.controller;

import main.model.Post;
import main.util.JacksonDecoder;
import main.util.JacksonEncoder;
import org.atmosphere.config.service.*;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.Broadcaster;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import java.util.concurrent.ExecutionException;

//@RestController
//@RequestMapping(value = "/users/posts/{id}")
//@Component:
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
//        if (topic == null)
//            System.out.println("null broadcaster");


    }

    @Message(encoders = {JacksonEncoder.class}, decoders = {JacksonDecoder.class})
    @DeliverTo(DeliverTo.DELIVER_TO.ALL)
    @Consumes("application/json")
    @POST
    public Post onMessage(Post post) throws ExecutionException, InterruptedException {
        return post;
    }

    @Disconnect
    public void disconnected(AtmosphereResourceEvent r) {
        System.out.println("dis connect");
    }

//    @Broadcast
//    public Broadcastable put(@Context final BroadcasterFactory factory, Post post) {
//        System.out.println("sd");
//        return new Broadcastable(factory.lookup(post.getId(), true));
//    }

}
