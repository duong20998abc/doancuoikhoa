package application.controller.web;


import application.data.model.Product;
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

@Controller
@RequestMapping(path = "/new")
public class NewController extends BaseController {
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/detail/{productid}")
//    public String index (Model model, @PathVariable int productId, @CookieValue("current-page") String currentPageCookie) {
//        System.out.println("-------------");
//        System.out.println(currentPageCookie);
//
//        ProductIndexVM vm = new ProductIndexVM();
//
//        Product existProduct = productService.findOne(productId);
//        if(existProduct != null) {
//            ModelMapper modelMapper = new ModelMapper();
//            ProductDetailModel productDetailModel = modelMapper.map(existProduct,ProductDetailModel.class);
//            vm.setInfo(productDetailModel);
//
//            this.setLayoutHeaderVM(vm);
//            model.addAttribute("vm",vm);
//            return "product/index";
//        }else {
//            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
//        }
//    }
}
