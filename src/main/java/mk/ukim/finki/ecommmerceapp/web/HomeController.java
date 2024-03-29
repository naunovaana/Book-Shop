package mk.ukim.finki.ecommmerceapp.web;

import mk.ukim.finki.ecommmerceapp.model.Author;
import mk.ukim.finki.ecommmerceapp.model.Category;
import mk.ukim.finki.ecommmerceapp.model.Product;
import mk.ukim.finki.ecommmerceapp.service.AuthorService;
import mk.ukim.finki.ecommmerceapp.service.CategoryService;
import mk.ukim.finki.ecommmerceapp.service.ProductService;
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
    public String getHomePage(Model model){
        List<Product> products=this.productService.findAll();
        model.addAttribute("products",products);
        return "home";

    }
    @GetMapping("/add-form")
    public String getAddPage(Model model){
        List<Author> authors = this.authorService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("authors",authors);
        model.addAttribute("categories",categories);
        model.addAttribute("product",new Product());
        return "add-products";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditPage(@PathVariable Long id,Model model){
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Category> categories = this.categoryService.listCategories();

            model.addAttribute("product",product );
            model.addAttribute("authors",authors );
            model.addAttribute("categories",categories);
            return "edit-products";
        }
        return "redirect:/products?error=ProductNotFound";
    }
    @PostMapping("/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam Double price,
                             @RequestParam Integer quantity,
                             @RequestParam Long category,
                             @RequestParam Long author){
         this.productService.save(name,price,quantity,category,author);
         return "redirect:/home";


    }
    @PostMapping("/edit/{id}")
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
    public String deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
        return "redirect:/home";
    }

}
