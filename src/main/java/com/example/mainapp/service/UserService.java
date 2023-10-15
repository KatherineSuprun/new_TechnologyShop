package com.example.mainapp.service;

import com.example.mainapp.dto.Roles;
import com.example.mainapp.entity.Role;
import com.example.mainapp.entity.User;
import com.example.mainapp.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.stereotype.Service;
import com.example.mainapp.repository.UserRepo;

@Service
@Slf4j // log
public class UserService { // регистрация и сохранение юзера

    // инжект инстансов
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public boolean createUser(User user) {
        String email = user.getEmail();
        if(userRepo.findByEmail(email) != null) return false;
        // если почта уже существует - фолс
        user.setActive(true); // установили активность
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.
        log.info("Saving new user with email: {}", email); // логирование
        userRepo.save(user); // сохранение в БД юзера
        return true;
    }
}
