package main.model;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@Table(name = "activity")
public class Activity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Reference
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @Reference
    @OneToOne(fetch = FetchType.EAGER)
    private Post post;

    @Column(name = "created")
    private Date created;

    @Reference
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type_name")
    private ActivityType activityType;

    public Activity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
}
