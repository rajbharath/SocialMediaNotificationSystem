package main.service;

import main.model.User;
import main.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = "src.main")
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User create(String name, String mail) {
        User user = new User();
        user.setName(name);
        user.setMail(mail);
        userRepo.save(user);
        return findById(user.getId());
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

    public List<User> findAll() {
        return userRepo.findAll();
    }
}
