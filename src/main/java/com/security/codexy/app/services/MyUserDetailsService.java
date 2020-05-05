package com.security.codexy.app.services;

import com.security.codexy.app.entities.MyUserDetails;
import com.security.codexy.app.entities.User;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("not user find"));


        return user.map(MyUserDetails ::new ).get();
    }
} // end class
