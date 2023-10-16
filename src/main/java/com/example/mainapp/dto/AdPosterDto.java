package com.example.mainapp.dto;

import com.example.mainapp.repository.ImageRepo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AdPosterDto {

    private String firstName;

    private String lastName;

    private int yearsOnSite;

    private String city;

    List<ImageRepo> reviews;

    List<ProductDto> advertisements;

}

