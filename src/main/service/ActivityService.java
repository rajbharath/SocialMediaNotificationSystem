package main.service;

import main.model.Activity;
import main.model.ActivityType;
import main.model.Post;
import main.model.User;
import main.repo.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    ActivityRepo activityRepo;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    public void addActivity(int userId, int postId, Date created, String activityType) {
        Activity activity = new Activity();
        User user = userService.findById(userId);
        Post post = postService.findById(postId);
        activity.setUser(user);
        activity.setPost(post);
        activity.setCreated(created);
        activity.setActivityType(ActivityType.valueOf(activityType.toUpperCase()));
        activityRepo.save(activity);

    }

    public List<Activity> findBy(int userId) {
        User user = userService.findById(userId);
        return activityRepo.findBy(user);
    }
}
