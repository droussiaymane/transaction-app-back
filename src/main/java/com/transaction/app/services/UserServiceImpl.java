package com.transaction.app.services;

import com.transaction.app.models.User;
import com.transaction.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public User findByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user=userRepository.findById(id).get();
        return user;
    }
}
