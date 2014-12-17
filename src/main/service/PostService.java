package main.service;

import main.model.Post;
import main.model.User;
import main.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;
    @Autowired
    UserService userService;

    public Post addPost(int userId,String message){
        Post post  = new Post();
        User user = userService.findById(userId);
        post.setMessage(message);
        post.setUser(user);
        post.setCreatedAt(Calendar.getInstance().getTime());
        postRepo.save(post);
        return post;
    }

    public List<Post> findByUserId(int id) {
        User user = userService.findById(id);
        List<User>  users =  user.getFriends();
        users.add(user);
        return postRepo.findByUser(users);
    }
}
