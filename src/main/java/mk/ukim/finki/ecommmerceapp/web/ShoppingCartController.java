package mk.ukim.finki.ecommmerceapp.web;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.ecommmerceapp.model.ShoppingCart;
import mk.ukim.finki.ecommmerceapp.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService service;

    public ShoppingCartController(ShoppingCartService service) {
        this.service = service;
    }

    @GetMapping
    public String getShoppingCart(Model model, HttpServletRequest request){
        String username=request.getRemoteUser();
        ShoppingCart shoppingCart=this.service.getActiveShoppingCart(username);
        model.addAttribute("products",this.service.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("shopping-cart",shoppingCart);
        return "shopping-cart";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToCart(@PathVariable Long id,HttpServletRequest request){
        try{
            String username= request.getRemoteUser();
            ShoppingCart shoppingCart=this.service.addProductsToShoppingCart(username,id);
            return "redirect:/shopping-cart";
        }catch (RuntimeException exception){
            return "redirect:/shopping-cart?error="+exception.getMessage();
        }
    }
}
