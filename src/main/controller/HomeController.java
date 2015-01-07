package main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.Path;

@RestController
@RequestMapping(value = "/")
@Path(value = "/")
public class HomeController {

    @RequestMapping
    public ModelAndView home() {
        return new ModelAndView("home.html");
    }
}
