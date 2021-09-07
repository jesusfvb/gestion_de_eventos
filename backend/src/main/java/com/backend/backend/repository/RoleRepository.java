package com.backend.backend.repository;

import java.util.List;

import com.backend.backend.repository.entity.MyRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<MyRole, Integer> {

    public MyRole findByRol(MyRole.Role role);

    public List<MyRole> findAllByUsersUsername(String username);

    public List<MyRole> findAllByUsersId(Integer id);

}
