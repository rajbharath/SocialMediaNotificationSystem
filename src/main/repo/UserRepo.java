package main.repo;

import main.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {
    @Autowired
    SessionFactory sessionFactory;

    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
//        int id = user.getId();
        transaction.commit();
        session.close();
    }


    public List<User> findByMail(String mail) {
        Session session = sessionFactory.openSession();
        Criteria criteria  = session.createCriteria(User.class);
        criteria.add(Restrictions.like("mail","%"+mail+"%"));
        List<User> users = criteria.list();
        //User user = (User) session.createQuery("from User U where mail= :mail").setParameter("mail", mail).uniqueResult();
        return users;
    }

    public User findById(int id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("from User U where id = :id").setParameter("id", id).uniqueResult();
        session.close();
        return user;
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("from User").list();
        session.close();
        return users;
    }

    public void update(User user) {

    }
}
