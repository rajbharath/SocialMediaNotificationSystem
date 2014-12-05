package main.controller;

import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan(basePackages = "src.main")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestParam(value = "name",required = true) String name,@RequestParam(value = "mail",required = true) String mail){
        User user = new User();
        user.setName(name);
        user.setMail(mail);
        userService.create(user);

    }

    @RequestMapping(method = RequestMethod.GET)
    public User findByMail(@RequestParam(value = "mail",required = true) String mail){
        return userService.findByMail(mail);
    }
}
