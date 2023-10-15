package com.example.mainapp.dto;

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

    List<ReviewDto> reviews;

    List<AdvertisementDto> advertisements;

}

