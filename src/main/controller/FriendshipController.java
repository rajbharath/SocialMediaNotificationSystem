package main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user/{id}/friendship")
public class FriendshipController {

    @RequestMapping(method = RequestMethod.POST)
    public void create(int userId, int friendId) {

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(int userId, int friendId) {

    }

}
