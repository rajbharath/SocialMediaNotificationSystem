package main.controller;

import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@ComponentScan(basePackages = "src.main")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "mail", required = true) String mail,@RequestParam(value = "password",required = true) String password) {
        return userService.create(name, mail,password);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> findByMail(@RequestParam(value = "mail", required = true) String mail) {
        List<User> users=  userService.findByMail(mail);
        return users;
    }

    @RequestMapping(value = "/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/")
    public Collection<User> findAll(){
        return userService.findAll();
    }
    @RequestMapping(value = "/{userId}/friend",method = RequestMethod.POST)
    public User addFriend(@RequestParam(value="friendId",required = true) int friendId,@PathVariable int userId){
        System.out.println(userId + "sd"+friendId);
        return userService.addFriend(userId,friendId);
    }

    @RequestMapping(value = "/{userId}/friend/{friendId}",method = RequestMethod.DELETE)
    public User deleteFriend(@PathVariable int friendId,@PathVariable int userId){
        System.out.println(userId + "sd"+friendId);
        return userService.deleteFriend(userId,friendId);
    }
}
