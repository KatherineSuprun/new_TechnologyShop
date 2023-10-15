package com.example.mainapp.repository;

import com.example.mainapp.entity.AdPoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdPosterRepo extends JpaRepository<AdPoster, Long> {

    List<AdPoster> findAll();

    Optional<AdPoster> findById(Long id);
}
