package main.model;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "notification")
public class Notification {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Reference
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @Reference
    @OneToOne(fetch = FetchType.EAGER)
    private Activity activity;

    @Column(name = "message")
    private String message;

    @Column(name = "isRead")
    private boolean isRead;

    public Notification() {
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
