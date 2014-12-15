package main.service;

import main.model.Post;
import main.repo.PostRepo;
import main.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class PostService {

    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;

    public Post addPost(int userId,String message){
        Post post  = new Post();
        return null;
    }
}
