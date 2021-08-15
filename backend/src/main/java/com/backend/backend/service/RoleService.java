package com.backend.backend.service;

import com.backend.backend.repository.entity.MyRole;
import com.backend.backend.repository.entity.MyUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    public List<MyRole> getByUsername(String username);

    public void addRoleUser(MyRole.Role role, MyUser user);

    public void removeAllRoleUserId(Integer id);

    public void addRemoveRoleNameUserId(Integer id, String[] roles);
}
