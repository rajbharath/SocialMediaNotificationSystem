package main.controller;

import main.model.Post;
import main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user/{id}/posts")
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

}
