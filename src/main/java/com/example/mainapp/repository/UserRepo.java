package com.example.mainapp.repository;

import com.example.mainapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByEmail(String email);

/*    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);*/

}
