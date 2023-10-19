package com.example.mainapp.dto;


import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProductDto {

    private String header;

    private String description;

    private LocalDate datePosted;

    private LocalDate dateUpdated;
}
