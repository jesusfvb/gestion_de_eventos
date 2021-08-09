package com.backend.backend.repository;

import com.backend.backend.repository.entity.MyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<MyRole, Integer> {

    @Query("select r from MyRole r where r.rol = ?1")
    public MyRole findByRole(MyRole.Role role);

}
