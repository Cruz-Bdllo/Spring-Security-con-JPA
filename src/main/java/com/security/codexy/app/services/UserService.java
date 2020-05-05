package com.security.codexy.app.services;

import com.security.codexy.app.entities.User;
import com.security.codexy.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase permite implementar las funcionalidades definidas en la interfaz IUserService
 * usando los métodos definidos en el repositorio de <b>UserRepository</b>
 */
@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    } // end of method for get all user

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByRfc(String rfc) {
        return Optional.of(userRepository.findUserByRfc(rfc).orElse(null));
    } // end of method for find one user throught their rfc

    @Override
    @Transactional(readOnly = false)
    public void saveUser(User user) {
        userRepository.save(user);
    } // end of method for save an user

    @Override
    @Transactional(readOnly = false)
    public void deleteUserByRfc(String rfc) {
        userRepository.deleteByRfc(rfc);
    } // end of method

} // end service
