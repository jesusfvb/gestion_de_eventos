package com.backend.backend.service.implementation;

import com.backend.backend.repository.RoleRepository;
import com.backend.backend.repository.entity.MyRole;
import com.backend.backend.repository.entity.MyUser;
import com.backend.backend.service.ConvocatoriaService;
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

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @Override
    public List<MyRole> getByUsername(String username) {
        List<MyRole> roles = roleRepository.findAllByUsersUsername(username);
        if (convocatoriaService.isConvocatoriaBoss(username)) {
            roles.add(new MyRole(MyRole.Role.COORDINADOR));
        }
        return roles;
    }

    @Override
    public List<MyRole> getByUserId(Integer id) {
        List<MyRole> roles = roleRepository.findAllByUsersId(id);
        if (convocatoriaService.isConvocatoriaBoss(id)) {
            roles.add(new MyRole(MyRole.Role.COORDINADOR));
        }
        return roles;
    }

    @Override
    public void addRoleUser(MyRole.Role role, MyUser user) {
        MyRole roleAux = roleRepository.findByRol(role);
        if (roleAux == null) {
            roleAux = roleRepository.save(new MyRole(role));
        }
        roleAux.getUsers().add(user);
        roleRepository.save(roleAux);
    }

    @Override
    public void removeAllRoleUserId(Integer id) {
       List<MyRole> roleList = roleRepository.findAllByUsersId(id);
        for(MyRole myRole:roleList){
            myRole.getUsers().removeIf(myUser -> myUser.getId().equals(id));
        }
        roleRepository.saveAll(roleList);
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
