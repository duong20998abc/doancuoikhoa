package application.controller.web;

import application.data.service.CategoryService;
import application.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/")
public class HomeController extends BaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "admin", method = RequestMethod.GET)
    public String admin(Model model, @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

        return "admin";
    }

    @GetMapping(path = "/")
    public String index(Model model,  HttpServletResponse response,
                        @RequestHeader("User-Agent") String userAgent,
                        HttpServletRequest request) {
        return "index";
    }

    @GetMapping(path = "/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping(path = "/memberProfile")
    public String memberProfile() {
        return "profile";
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }
}
