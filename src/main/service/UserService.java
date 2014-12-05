package main.service;

import main.model.User;
import main.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = "src.main")
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void create(User user) {
        userRepo.save(user);
    }

    public User findByMail(String mail) {
//        User user = new User();
//        user.setId(1);
//        user.setMail(mail);
//        user.setName("bharath");
        return userRepo.findByMail(mail);
    }

    public User findById(int id) {
        return userRepo.findById(id);
    }
}
