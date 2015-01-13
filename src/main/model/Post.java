package main.model;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@Table(name = "post")
public class Post {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String message;

    @Reference
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "created_at")
    private Date createdAt;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (user != null ? !user.equals(post.user) : post.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    @com.fasterxml.jackson.annotation.JsonIgnore
    public String toString() {
        return "Post{" +
                "id:" + id +
                ", message:'" + message + '\'' +
                ", user:" + user.getId() +
                ", createdAt:" + createdAt +
                '}';
    }
}
