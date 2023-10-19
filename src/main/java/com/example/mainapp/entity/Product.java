package com.example.mainapp.entity;

import com.example.mainapp.repository.ProductRepo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products.html")
public class Product {


    @Id
    private Long id;

    @Column(name = "product_title")
    private String productTitle;

    private String description;

    @Column(name = "date_posted")
    private LocalDate datePosted;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @Column(name = "price")
    private int price;

    @Column(name = "city")
    private String city;

    @ManyToOne
    private AdPoster adPosters;


    // ALL чтобы удаляло абсолютно все фото в связке при удалении этого товара,
    // + при сохранении товара список фотографий будет с ним сохранятся и сущности
    // LAZY не нужно подгружать остальные фото при подгрузке 1 товара(все его фото)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "product") // товар связанный с фото будет FK
    private List<Image> images = new ArrayList<>();
    private Long previewImageId; // превьюшка ли это фото на главной странице
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)// рефреш - при удалении
    // товара чтоб не снесло юзера, лейзи чтоб не подгружать имя автора товара при загрузке товара

    @JoinColumn // FK product with user
    private User user;
    private LocalDateTime dateOfCreated; // когда был создан товар

    private void init() {
        dateOfCreated = LocalDateTime.now();
    }





    public void addImageToProduct(Image image) {
        image.setProduct(this); // установили текущий товар
        images.add(image);
    }

    public Product getTitle() {
        return getTitle();
    }

}