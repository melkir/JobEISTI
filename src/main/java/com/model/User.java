package com.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "First name is required.")
    private String firstName;
    @NotEmpty(message = "Last name is required.")
    private String lastName;
    @Email(message = "Please provide a valid email address.")
    @NotEmpty(message = "Email is required.")
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @NotEmpty(message = "Password is required.")
    private String password;
    @CreatedDate
    private Date creationDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>(0);
    @OneToMany(mappedBy = "creator")
    private Set<Event> eventsCreated = new HashSet<>(0);
    @OneToMany(mappedBy = "creator")
    private Set<Offer> offersCreated = new HashSet<>(0);
    @OneToMany(mappedBy = "creator")
    private Set<Resource> resourcesCreated = new HashSet<>(0);
    @OneToMany(mappedBy = "creator")
    private Set<Newsletter> newsletterCreated = new HashSet<>(0);

    public User() {
    }

    public User(User user) {
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.password = user.password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void addRole(String role) {
        this.userRoles.add(new UserRole(this, role));
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Set<Event> getEventsCreated() {
        return eventsCreated;
    }

    public Set<Offer> getOffersCreated() {
        return offersCreated;
    }

    public Set<Resource> getResourcesCreated() {
        return resourcesCreated;
    }

    public Set<Newsletter> getNewsletterCreated() {
        return newsletterCreated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                ", userRoles=" + userRoles +
                '}';
    }

}

