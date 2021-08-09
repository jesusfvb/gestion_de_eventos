package com.backend.backend.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public String logIn(String userName, String password);

}
