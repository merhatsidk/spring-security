package edu.miu.cs545.restApi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {

    public User(String userName, String password, boolean isAvailable, List<Post> posts, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.isAvailable = isAvailable;
        this.posts = posts;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private boolean isAvailable;

    @OneToMany(cascade = CascadeType.ALL)
//    @Fetch(value = FetchMode.SELECT)
//    @BatchSize(size = 5)
    @JoinColumn(name = "posts_userId")
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER) @JoinTable
    private List<Role> roles;
}
