package com.example.mainapp.entity;

import com.example.mainapp.dto.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерация уникального значения
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email", unique = true) // валидация уникального имейла для БД
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "active")
    private boolean active; // пока пользователь не подтвердит аккаунт - он будет неактивным\кидать в бан

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn // fk
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // при удалении юзера удалится всё
    @JoinColumn(name = "image_id")

    private Image avatar;

    @Column(name = "password", length = 1000) // будет добавлено шифрование пароля
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))

    @Enumerated(EnumType.STRING)  // преобразовать enum в тип String (конвертация)
    private Set<Roles> roles = new HashSet<>(); // id юзеров и их роли таблица

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Product> products = new ArrayList<>();


    private LocalDateTime dateOfCreated; // дата создания аккаунта

    public User(User user) {
    }

    public boolean isAdmin() {
        return roles.contains(Roles.ADMIN);
    }
    @Bean
    private void init() { // инициализация даты создания
        dateOfCreated = LocalDateTime.now(); //
    }

    public User getProductList() {
        return getProductList();
    }
}
