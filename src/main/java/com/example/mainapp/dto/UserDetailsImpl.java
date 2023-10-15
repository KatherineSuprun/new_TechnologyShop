package com.example.mainapp.dto;

import com.example.mainapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor // генерирует конструктор с 1 параметром для каждого поля
@Data // заменяет @Getter @Setter и @RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {   //для использования аутентификации (Spring Security) нужно реализовать интерфейс
    // инжектим юзера
    private final User user;


    @Override // от UserDetailsImpl
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
