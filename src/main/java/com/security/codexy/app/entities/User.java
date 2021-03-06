package com.security.codexy.app.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static long serialVersionUID = 1L;

    /*      P R O P E R T I E S
    --------------------------------- */
    @Id
    private String rfc;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String email;

    @Range(min = 18, max = 120)
    private int age;

    private String password;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "rfc")},
            inverseJoinColumns = {@JoinColumn(name = "id_autho")}
    )
    private List<Authority> authorities;

    @PrePersist
    public void init(){
        authorities = new ArrayList<Authority>();
    } // end pre-persist

    /*        M E T H O D S
    --------------------------------- */
    public User() {authorities = new ArrayList<Authority>();}
    public User(String rfc, String firstName, String lastName, String email, int age) {
        this.rfc = rfc;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    } // end constructors

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthorityToList(Authority authority){
        this.authorities.add(authority);
    }

} // end class
