package com.example.mainapp.service;
import com.example.mainapp.dto.Roles;
import com.example.mainapp.entity.User;
import com.example.mainapp.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.mainapp.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
@Slf4j // log
public class UserService { // регистрация и сохранение юзера

    // инжект инстансов
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    //private final BCryptPasswordEncoder encoder;

    public UserService(UserRepo userRepo,
                       RoleRepo roleRepo/*, BCryptPasswordEncoder encoder*/) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
      //  this.encoder = encoder;
    }


    public boolean createUser(User user) {
        String email = user.getEmail();
        if(userRepo.findByEmail(email) != null) return false;
        // если почта уже существует - фолс
        user.setActive(true); // установили активность
       // user.setPassword(encoder.encode(user.getPassword()));
        user.getRoles().add(Roles.USER);
        log.info("Saving new user with email: {}", email); // логирование
        userRepo.save(user); // сохранение в БД юзера
        return true;
    }
}
