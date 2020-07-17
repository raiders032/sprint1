package com.swm.sprint1.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @OneToMany(mappedBy = "user")
    private Set<UserCategory> categories = new HashSet<>();

    public User(String name, String email, String password) {

    }

    public User(String name, String email, String password, AuthProvider provider) {
        this.name=name;
        this.email=email;
        this.password=password;
        this.provider=provider;
    }

    public User(String name, String email, String imageUrl, AuthProvider provider, String providerId) {
        this.name=name;
        this.email=email;
        this.imageUrl=imageUrl;
        this.provider=provider;
        this.providerId=providerId;
    }

    public void update(String name, String imageUrl) {
        this.name=name;
        this.imageUrl=imageUrl;
    }

    public void changeName(String name) {
        this.name=name;
    }

    public void changeUserCategory(Set<UserCategory> userCategories) {
        this.categories=userCategories;
        userCategories.forEach(userCategory -> userCategory.changeUser(this));
    }
}
