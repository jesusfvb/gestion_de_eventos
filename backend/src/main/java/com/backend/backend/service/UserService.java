package com.backend.backend.service;

import com.backend.backend.repository.entity.MyUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public String logIn(String userName, String password);

    public List<MyUser> list();

    public MyUser getById(Integer id);

    public MyUser getByUsername(String username);

    public void save(String name, String surname, String username, Integer dni);

    public void delete(Integer[] ids);

    public void update(Integer id, String name, String surname, String username, Integer dni);

}
