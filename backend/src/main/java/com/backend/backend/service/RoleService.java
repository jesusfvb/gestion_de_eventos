package com.backend.backend.service;

import com.backend.backend.repository.entity.MyRole;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    public MyRole getRole(MyRole.Role role);

}
