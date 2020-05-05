package com.security.codexy.app.services;

import com.security.codexy.app.entities.Authority;
import com.security.codexy.app.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorityService implements IAuthorityService{
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Authority> findAllAuthorities() {
        return (List<Authority>) authorityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Authority findAuthorityById(int idAuthority) {
        return authorityRepository.findById(idAuthority).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAuthority(int idAuthority) {
        authorityRepository.deleteById(idAuthority);
    }
} // end service
