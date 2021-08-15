package com.backend.backend.service.implementation;

import com.backend.backend.repository.RoleRepository;
import com.backend.backend.repository.entity.MyRole;
import com.backend.backend.repository.entity.MyUser;
import com.backend.backend.service.RoleService;
import com.backend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<MyRole> getByUsername(String username) {
        return roleRepository.findAllByUsername(username);
    }

    @Override
    public void addRoleUser(MyRole.Role role, MyUser user) {
        MyRole roleAux = roleRepository.findByRole(role);
        if (roleAux == null) {
            roleAux = roleRepository.save(new MyRole(role));
        }
        roleAux.getUsers().add(user);
        roleRepository.save(roleAux);
    }

    @Override
    public void removeAllRoleUserId(Integer id) {
        roleRepository.deleteRoleUserById(id);
    }

    @Override
    public void addRemoveRoleNameUserId(Integer id, String[] roles) {
        MyUser user = userService.getById(id);
        removeAllRoleUserId(id);
        for (String roleName : roles) {
            addRoleUser(MyRole.Role.valueOf(roleName), user);
        }
    }
}
