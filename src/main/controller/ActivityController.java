package main.controller;

import main.model.Activity;
import main.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "user/{userId}/activities")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@PathVariable int userId, @RequestParam(value = "postId") int postId, @RequestParam(value = "activityType") String activityType) {
        activityService.addActivity(userId, postId, Calendar.getInstance().getTime(), activityType);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Activity> findBy(@PathVariable int userId) {
        return activityService.findBy(userId);
    }

}
