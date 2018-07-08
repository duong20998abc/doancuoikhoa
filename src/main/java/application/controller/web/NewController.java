package application.controller.web;


import application.data.model.New;
import application.data.model.Product;
import application.data.service.NewService;
import application.data.service.ProductService;
import application.model.ProductDetailModel;
import application.viewmodel.productindex.ProductIndexVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/new")
public class NewController extends BaseController {

    @Autowired
    private NewService newService;

    public String news(Model model){
        ArrayList<New> news = newService.getAll();
        model.addAttribute("news",news);
        return "news";
    }

}
