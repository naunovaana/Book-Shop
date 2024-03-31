package mk.ukim.finki.ecommmerceapp.web;

import mk.ukim.finki.ecommmerceapp.model.Category;
import mk.ukim.finki.ecommmerceapp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategories(Model model){
        List<Category> categories=this.categoryService.listCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("bodyContent","categories");
        return "master-template";
    }



}
