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

    @Query("select r from MyRole r where r.rol = ?1")
    public MyRole findByRole(MyRole.Role role);

    @Query(value = "select r.* from my_role r join my_role_users ru on r.id = ru.my_role_id join my_user u on ru.users_id = u.id where u.username = ?1", nativeQuery = true)
    public List<MyRole> findAllByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "delete from my_role_users ru where ru.users_id = ?1", nativeQuery = true)
    public void deleteRoleUserById(Integer id);
}
