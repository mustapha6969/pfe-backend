package com.mustapha.backend.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mustapha.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @JoinTable(
            name = "project_users",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> developers = new ArrayList<>();

    // Helper method to manage the bidirectional relationship
    public void addDeveloper(User user) {
        developers.add(user);
        user.getProjects().add(this);
    }

    public void removeDeveloper(User user) {
        developers.remove(user);
        user.getProjects().remove(this);
    }
}
