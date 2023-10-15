package com.example.mainapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posters")
public class AdPoster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "years_on_site")
    private int yearsOnSite;

    private String city;

    @OneToMany// просмотреть все свои объявления
    private List<Advertisement> adList;

    @OneToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "posters_advertisement",
    joinColumns = @JoinColumn(name = "posters_id"))
    private List<Advertisement> advertisements;

}
