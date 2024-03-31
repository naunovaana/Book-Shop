package mk.ukim.finki.ecommmerceapp.web;

import mk.ukim.finki.ecommmerceapp.model.Author;
import mk.ukim.finki.ecommmerceapp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthors(Model model){
        List<Author> authors=this.authorService.findAll();

        model.addAttribute("authors",authors);
        model.addAttribute("bodyContent","authors");
        return "master-template";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id){
        this.authorService.deleteById(id);
        return "redirect:/authors";

    }

}
