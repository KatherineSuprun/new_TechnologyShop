package com.example.mainapp.service;

import com.example.mainapp.repository.AdvertisementRepo;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final AdvertisementRepo advertisementRepo;

    public AdvertisementService(AdvertisementRepo advertisementRepo) {
        this.advertisementRepo = advertisementRepo;
    }
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
//                .reviews(entity.getReview().stream().map(rev -> ReviewDto.builder()
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