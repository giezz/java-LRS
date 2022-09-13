package giezz.controller;

import giezz.model.Product;
import giezz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductService productsService;

    @Autowired
    public void setProductsService(ProductService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(
            Model model,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "0") int page
    ) {
        Page<Product> productPage = productsService.pageGetAll(PageRequest.of(page, size));
        Product product = new Product();
        model.addAttribute("productPage", productPage);
        model.addAttribute("numbers", IntStream.range(0, productPage.getTotalPages()).toArray());
        model.addAttribute("products", productsService.getAll());
        model.addAttribute("product", product);
        model.addAttribute("top3product", productsService.findTop3());
        return "products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/remove/{id}")
    public String removeProduct(@PathVariable Long id) {
        productsService.remove(id);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        product.setCount(product.getCount() + 1);
        productsService.edit(product);
        model.addAttribute("product", product);
        return "product-page";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showProductToEdit(Model model, @PathVariable Long id) {
        model.addAttribute("product", productsService.getById(id));
        return "product-edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/change")
    public String editProduct(@ModelAttribute Product product) {
        productsService.edit(product);
        return "redirect:/products";
    }

    @PostMapping("/find")
    public String filterProduct(
            @RequestParam String title,
            @RequestParam int minPrice,
            @RequestParam int maxPrice,
            @RequestParam(required = false, defaultValue = "3") int size,
            @RequestParam(required = false, defaultValue = "0") int page,
            Model model
    ) {
        Page<Product> productPage;
        if (!title.isEmpty() && !String.valueOf(minPrice).isEmpty() && !String.valueOf(maxPrice).isEmpty()) {
            productPage = productsService.getFilteredList(
                    title, minPrice, maxPrice, PageRequest.of(page, size)
            );
        } else {
            productPage = productsService.pageGetAll(PageRequest.of(page, size));
        }
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("products", productPage);
        model.addAttribute("productPage", productPage);
        model.addAttribute("numbers", IntStream.range(0, productPage.getTotalPages()).toArray());
        model.addAttribute("fTitle", title);
        model.addAttribute("fMinPrice", minPrice);
        model.addAttribute("fMaxPrice", maxPrice);
        return "products";
    }
}