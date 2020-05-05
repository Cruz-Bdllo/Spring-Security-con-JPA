package com.security.codexy.app.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String email;
    private String password;
    private boolean enabled;
    private List<GrantedAuthority> authorityList;

    public MyUserDetails(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();

        // Assing all authorities
        for (Authority autho : user.getAuthorities()){
            authorityList.add(new SimpleGrantedAuthority(autho.getName()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


} // end class
