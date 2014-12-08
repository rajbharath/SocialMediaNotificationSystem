package main.controller;

import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan(basePackages = "src.main")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "mail", required = true) String mail) {
        return userService.create(name, mail);
    }

    @RequestMapping(method = RequestMethod.GET)
    public User findByMail(@RequestParam(value = "mail", required = true) String mail) {
        return userService.findByMail(mail);
    }

    @RequestMapping(value = "/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/")
    public List<User> findAll(){
        return userService.findAll();
    }
}
