package main.repo;

import main.model.Post;
import main.model.User;
import org.hibernate.Query;
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

    public List<Post> findByUser(List<User> users) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Post P where P.user in :users order by P.createdAt desc");

        query.setParameterList("users", users);
//        Criteria criteria = session.createCriteria(Post.class,"POST");
//        criteria.addOrder(Order.desc("createdAt"));
//        criteria.add(Restrictions.eq("user",user));
////        List<Post> posts = session.createQuery("from Post P where user_id = :userId").setParameter("userId", userId).list();
        List<Post> posts = query.list();
//        for (Post post : posts)
//            System.out.println("user id"+post.getUser().getId()+"post: " + post.getMessage()+" "+post.getId()+"size"+posts.size());
        session.close();
        return posts;
    }
}
