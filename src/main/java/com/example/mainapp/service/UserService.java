package com.example.mainapp.service;

import com.example.mainapp.dto.Roles;
import com.example.mainapp.entity.User;
import com.example.mainapp.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.mainapp.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j // log
public class UserService { // регистрация и сохранение юзера

    // инжект инстансов
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo,
                       RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }


    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepo.findByEmail(email) != null) return false;
        // если почта уже существует - фолс
        //user.setActive(true); // установили активность
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Roles.USER);
        log.info("Saving new user with email: {}", email); // логирование
        userRepo.save(user); // сохранение в БД юзера
        return true;
    }

    /*
        public void BanUser(Long id) {
            User user=userRepo.findById(id).orElse(null);
            if(user !=null){
                if ((user.isActive())){
                    user.setActive(false);
                    log.info("Ban user with id={}; ; email;{]",user.getId(),user.getEmail());
                }else  user.setActive(true);
                user.setActive(false);
                log.info("UnBan user with id={}; ; email;{]",user.getId(),user.getEmail());
            }
            userRepo.save(user);
        }
    */
    public List<User> list() {
        return userRepo.findAll();
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Roles.values())
                .map(Roles::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Roles.valueOf(key));
            }
        }
        userRepo.save(user);
    }
}
