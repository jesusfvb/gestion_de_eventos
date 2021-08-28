package com.backend.backend.repository;

import com.backend.backend.repository.entity.MyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<MyRole, Integer> {

    public MyRole findByRol(MyRole.Role role);

    public List<MyRole> findAllByUsersUsername(String username);

    public List<MyRole> findAllByUsersId(Integer id);

}
