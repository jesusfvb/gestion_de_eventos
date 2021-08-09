package com.backend.backend.service;

import com.backend.backend.repository.entity.MyUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public MyUser getByUsername(String username);

    public void save(String username, String password);

    // Authentication
    public String logIn(String userName, String password);

}
