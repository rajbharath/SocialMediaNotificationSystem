package main.repo;

import main.model.Activity;
import main.model.ActivityType;
import main.model.Post;
import main.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityRepo {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Activity activity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(activity);
        transaction.commit();
        session.close();
    }

    public void update(Activity activity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(activity);
        transaction.commit();
        session.close();
    }

    public void delete(Activity activity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(activity);
        transaction.commit();
        session.close();
    }

    public List<Activity> findBy(User user, ActivityType activityType) {
        return null;
    }

    public List<Activity> findBy(User user, ActivityType activityType, Post post) {
        return null;
    }

    public List<Activity> findBy(User user) {
        Session session = sessionFactory.openSession();
        List<Activity> activities = session.createQuery("from Activity A where A.user= :user").setParameter("user", user).list();
        session.close();
        return activities;
    }

    public List<Activity> findBy(List<User> users) {
        Session session = sessionFactory.openSession();
        List<Activity> activities = session.createQuery("from Activity A where A.user in :users").setParameterList("users", users).list();
        session.close();
        return activities;
    }

    public Activity findBy(int activityId) {
        Session session = sessionFactory.openSession();
        Activity activity = (Activity) session.createQuery("from Activity A where A.id = :id").setParameter("id", activityId).uniqueResult();
        session.close();
        return activity;
    }
}
