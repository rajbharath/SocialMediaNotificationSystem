package main.repo;

import main.model.Post;
import main.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepo {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Post post){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(post);
        transaction.commit();
        session.close();
    }

    public void update(Post post){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(post);
        transaction.commit();
        session.close();
    }

    public void delete(Post post){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(post);
        transaction.commit();
        session.close();
    }

    public List<Post> findByUserId(int userId) {
        Session session = sessionFactory.openSession();
        List<Post> posts = session.createQuery("from User U where user_id = :userId").setParameter("userId", userId).list();
        session.close();
        return posts;
    }
}
