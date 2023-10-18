package com.example.mainapp.controller;

import com.example.mainapp.entity.Product;
import com.example.mainapp.service.ProductService;
import javassist.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RequestMapping
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String product(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("product", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) throws NotFoundException {
        model.addAttribute("product", productService.getProductById(id));
        return "products-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file") MultipartFile file1, @RequestParam("file") MultipartFile file2
            , @RequestParam("file") MultipartFile file3, Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}