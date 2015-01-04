package main.controller;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import main.model.Post;
import main.service.PostService;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.config.service.*;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.interceptor.SuspendTrackerInterceptor;
import org.atmosphere.jersey.SuspendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user/{id}/posts")
@ManagedService(path = "user/{id}/posts")
@Singleton
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(method = RequestMethod.POST)
    public Post create(@PathVariable int id, @RequestParam(value = "message") String message) {
        return postService.addPost(id, message);
    }

    @RequestMapping(method = RequestMethod.GET)

    public List<Post> findPostForUser(@PathVariable int id) {
        List<Post> posts = postService.findByUserId(id);
        return posts;
    }

    @Message
    public void message(){
        System.out.println("on message");

    }
    @Ready
    public void ready(){
        System.out.println("on ready");

    }

    @Suspend
    public void suspend(){
        System.out.println("on suspen");

    }


}
