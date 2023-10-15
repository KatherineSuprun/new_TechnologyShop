package com.example.mainapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "advertisement")
public class Advertisement {

    @Id
    private Long id;

    @Column(name = "advertisement_title")
    private String advertisementTitle;

    private String description;

    @Column(name = "date_posted")
    private LocalDate datePosted;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @ManyToMany(mappedBy = "advertisement")
    private List<AdPoster> adPosters;

}
