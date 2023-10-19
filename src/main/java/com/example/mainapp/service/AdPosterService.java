package com.example.mainapp.service;
import com.example.mainapp.repository.AdPosterRepo;
import org.springframework.stereotype.Service;


@Service
public class AdPosterService {

    private final AdPosterRepo adPosterRepo;

    public AdPosterService(AdPosterRepo adPosterRepo) {
        this.adPosterRepo = adPosterRepo;
    }
    }
