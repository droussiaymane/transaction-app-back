package com.transaction.app.services;

import com.transaction.app.models.User;

public interface UserService {

    User findByEmail(String email);
    User findById(Long id);

}
