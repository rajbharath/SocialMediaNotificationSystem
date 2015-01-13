package main.repo;

import main.model.Notification;
import main.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepo {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Notification notification) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(notification);
        transaction.commit();
        session.close();
    }

    public void update(Notification notification) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(notification);
        transaction.commit();
        session.close();
    }

    public void delete(Notification notification) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(notification);
        transaction.commit();
        session.close();
    }

    public List<Notification> findBy(List<User> users) {
        return null;
    }

    public List<Notification> findBy(User user) {
        return null;
    }
}
