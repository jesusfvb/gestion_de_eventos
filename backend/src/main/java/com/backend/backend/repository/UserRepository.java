package com.backend.backend.repository;

import com.backend.backend.repository.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Integer> {

}
