package com.example.mainapp.controller;

import com.example.mainapp.entity.Product;
import com.example.mainapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

/*@Controller
@RequestMapping
public class ProductController {

    @GetMapping("/")
    // передаем список товаров с контроллера шаблонизатору
    public String products(@RequestParam(name = "title", required = false) String title, Principal
            principal, ModelMap model, ProductService productService) {
        model.addAttribute("products", productService.listProducts(title));
        // скрыть возможность добавление товара для не зареганых пользователей
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                ProductService productService, Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/"; // редирект на главную страницу, появится товар
    }

    // принимаем id товара который нужно удалить
    @PostMapping("/product/delete/{id}") // id будет преобразовываться в тип long
    public String deleteProduct(@PathVariable Long id, ProductService productService) { // как получить эту переменную
        productService.deleteProduct(id);
        return "redirect:/"; // удаление товара и переход на главную страницу
    }

    // просмотр подробной инфы о каждом товаре
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Product product) { // Модель для передачи данных, элементов
        //товар с этим айдишником передаю в модель и отображаю
        product.getProductById(product, id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }
}*/
