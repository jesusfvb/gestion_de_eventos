package com.backend.backend.service.implementation;

import com.backend.backend.repository.RoleRepository;
import com.backend.backend.repository.entity.MyRole;
import com.backend.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public MyRole getRole(MyRole.Role role) {
        MyRole roleAux = roleRepository.findByRole(role);
        if (roleAux != null) {
            return roleAux;
        }
        return roleRepository.save(new MyRole(role));
    }
}
