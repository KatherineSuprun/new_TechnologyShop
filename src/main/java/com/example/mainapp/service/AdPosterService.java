package com.example.mainapp.service;

import com.example.mainapp.repository.AdPosterRepo;
import org.springframework.stereotype.Service;

@Service
public class AdPosterService {

    private final AdPosterRepo adPosterRepoRepo;

    public AdPosterService(AdPosterRepo adPosterRepoRepo) {
        this.adPosterRepoRepo = adPosterRepoRepo;
    }


//    /**
//     * To-do: add filters, pagination, dynamic sorting
//     *
//     */
//    public List<ArtistDto> findAllArtists() {
//        return artistRepo.findAll().stream().map(entity -> ArtistDto.builder()
//                .firstName(entity.getFirstName())
//                .lastName(entity.getLastName())
//                .education(entity.getEducation())
//                .reviews(entity.getReview().stream().map(rev -> ImageDto.builder()
//                                .message(rev.getMessage())
//                                .title(rev.getTitle())
//                                .mark(rev.getMark())
//                                .build())
//                        .collect(Collectors.toList()))
//                .vacancies(entity.getVacancies().stream().map(vac -> VacancyDto.builder()
//                        .header(vac.getVacancyTitle())
//                        .description(vac.getDescription())
//                        .datePosted(vac.getDatePosted())
//                        .dateUpdated(vac.getDateUpdated())
//                        .build()
//                ).collect(Collectors.toList()))
//                .build()).collect(Collectors.toList());
//    }
}
