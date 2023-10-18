package com.example.mainapp.service;

import com.example.mainapp.controller.ProductController;
import com.example.mainapp.entity.Image;
import com.example.mainapp.entity.Product;
import com.example.mainapp.entity.User;
import com.example.mainapp.repository.ProductRepo;
import com.example.mainapp.repository.UserRepo;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private List<Product> products = new ArrayList<>();
    private long ID = 0;

    public ProductService(ProductRepo productRepo, UserRepo userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public List<Product> getProducts(String title) {
        return productRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product ,title {}, Author {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDB = productRepo.save(product);
        productFromDB.setPreviewImageId(productFromDB.getImages().get(0).getId());
        product.setId(++ID);
        products.add(product);
        productRepo.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();//      файл в картинку
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public Product getProductById(Long id) throws NotFoundException {
        return products.stream().findFirst().orElseThrow(()
                -> new NotFoundException(String.format("Product with id %id was not found.")));

    }

    // просмотр всех товаров
    public List<Product> listProducts(String title) {
        return products;
    }
}
