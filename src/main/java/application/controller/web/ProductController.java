package application.controller.web;

import application.data.model.Product;
import application.data.model.User;
import application.data.service.ProductService;
import application.data.service.UserService;
import application.model.ProductDetailModel;
import application.model.ProductName;
import application.viewmodel.productindex.ProductIndexVM;
import application.viewmodel.productindex.ProductSearchVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/detail/{productId}")
    public String index (Model model, @PathVariable int productId, @CookieValue("current-page") String currentPageCookie) {
        System.out.println("-------------");
        System.out.println(currentPageCookie);

        ProductIndexVM vm = new ProductIndexVM();

        String  username = SecurityContextHolder.getContext().getAuthentication().getName();

        User listUsers = userService.findUserByUsername(username);
        vm.setUser(listUsers);

        Product existProduct = productService.findOne(productId);
        if(existProduct != null) {
            ModelMapper modelMapper = new ModelMapper();
            ProductDetailModel productDetailModel = modelMapper.map(existProduct,ProductDetailModel.class);
            vm.setInfo(productDetailModel);

            this.setLayoutHeaderVM(vm);
            model.addAttribute("vm",vm);
            return "product/index";
        }else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchProduct(@ModelAttribute ProductName productName, BindingResult errors, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        ProductSearchVM productSearchVM = new ProductSearchVM();
        try {
            ArrayList<Product> products = new ArrayList<>();
            ArrayList<ProductDetailModel> productDetailModels = new ArrayList<>();
            products =  productService.findByNameContaining(productName.getName());
            for(Product p : products){
                productDetailModels.add(modelMapper.map(p,ProductDetailModel.class));
            }
            productSearchVM.setKeyWord(productName.getName());
            productSearchVM.setProductDetailModels(productDetailModels);
            model.addAttribute("vm",productSearchVM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/search";
    }
}
