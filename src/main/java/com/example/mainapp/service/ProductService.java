package com.example.mainapp.service;

import com.example.mainapp.entity.Image;
import com.example.mainapp.entity.Product;
import com.example.mainapp.entity.User;
import com.example.mainapp.repository.ProductRepo;
import com.example.mainapp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;
    private UserRepo userRepo;

    public ProductService(ProductRepo productRepo, UserRepo userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    // просмотр всех товаров
   /* public List<Product> listProducts(String title) {
        if (title != null) productRepo.findByTitle(title); // метод благодаря jpa
        return productRepo.findAll();
    }*/

    // сохранение, функция добавления 3-ёх картинок к товару, Principal - состояние приложения у юзеров

    public void saveProduct(Principal principal, Product product, MultipartFile file1,
                            MultipartFile file2, MultipartFile file3) throws IOException {

        //product.setUser(getUserByPrincipal(principal));// присвоить товар определенному юзеру
        Image image1;
        Image image2;
        Image image3;
        // если размер фото не 0 (тоесть её нет), то image преобразовывем из MultipartFile в фото
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true); // если картинка первая, то она - превью
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file1);
            product.addImageToProduct(image3);
        }

        log.info("Saving new Product.Title : {}; Author email: {}",
                product.getTitle(), product.getUser().getEmail()); // подставит строковое представление продукта
        Product productFromDb = productRepo.save(product);// получаем новый товар
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepo.save(product); // обновление и сохранение товара с айди првеью
    }

    public Optional<User> getUserByPrincipal(Principal principal) {
        if (principal == null) return Optional.of(new User());
        return userRepo.findByEmail(principal.getName());
    }


    // метод для преобразрования файла в картинку

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName()); // назначаем имя из файла
        image.setOriginalFileName(file.getOriginalFilename()); // назначаем оригинальное имя
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    // удаление товара по id

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
    // найти товар по id, если нету - вернуть null
/*    public Product getProductById(Product products, Long id) { // если товара с таким id нету - null
        for (Product product : products) {
            if (product.getId().equals(id))
                return product;
        }
        return null;
    }*/

