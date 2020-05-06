package com.security.codexy.app.services;

import com.security.codexy.app.entities.MyUserDetails;
import com.security.codexy.app.entities.User;
import com.security.codexy.app.repositories.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByEmail(username);
        user.orElseThrow(() -> new UsernameNotFoundException("not user find"));

        return user.map(MyUserDetails::new).get();
    }
} // end class
