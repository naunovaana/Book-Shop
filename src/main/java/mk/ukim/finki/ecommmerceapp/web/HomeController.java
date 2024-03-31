package mk.ukim.finki.ecommmerceapp.web;

import mk.ukim.finki.ecommmerceapp.model.Author;
import mk.ukim.finki.ecommmerceapp.model.Category;
import mk.ukim.finki.ecommmerceapp.model.Product;
import mk.ukim.finki.ecommmerceapp.service.AuthorService;
import mk.ukim.finki.ecommmerceapp.service.CategoryService;
import mk.ukim.finki.ecommmerceapp.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final ProductService productService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, AuthorService authorService, CategoryService categoryService) {
        this.productService = productService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error,@RequestParam(required = false) String nameSearch,Model model){
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products;
        if (nameSearch == null) {
            products = this.productService.findAll();
        } else {
            products = this.productService.listProductsByName(nameSearch);
        }
        model.addAttribute("products",products);
        model.addAttribute("bodyContent","home");
        return "master-template";

    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddPage(Model model){
        List<Author> authors = this.authorService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("authors",authors);
        model.addAttribute("categories",categories);
        model.addAttribute("product",new Product());
        model.addAttribute("bodyContent","add-products");
        return "master-template";
    }
    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditPage(@PathVariable Long id,Model model){
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Category> categories = this.categoryService.listCategories();

            model.addAttribute("product",product );
            model.addAttribute("authors",authors );
            model.addAttribute("categories",categories);
            model.addAttribute("bodyContent","edit-products");
            return "master-template";
        }
        return "redirect:/home?error=ProductNotFound";
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduct(@RequestParam String name,
                             @RequestParam Double price,
                             @RequestParam Integer quantity,
                             @RequestParam Long category,
                             @RequestParam Long author){
         this.productService.save(name,price,quantity,category,author);
         return "redirect:/home";


    }
    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(@PathVariable("id") Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long author){
        this.productService.edit(id,name,price,quantity,category,author);
       return "redirect:/home";

    }


    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
        return "redirect:/home";
    }
    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent","access-denied");
        return "master-template";
    }


}
