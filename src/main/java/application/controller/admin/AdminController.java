package application.controller.admin;

import application.constant.Constant;
import application.controller.web.BaseController;
import application.data.model.Category;
import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.CategoryDataModel;
import application.viewmodel.admin.AdminVM;
import application.viewmodel.common.ProductVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping(path="/admin")

public class AdminController extends BaseController{

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/manage_product")
    public String admin(Model model, @RequestParam(value = "pageNumber", required = false)Integer pageNumber) {
        int pageSize = Constant.DEFAULT_PAGE_SIZE;

        AdminVM vm = new AdminVM();
        long totalProducts = productService.getTotalProducts();
        ArrayList<Product> allProducts = productService.getAll();
        vm.setListAllProducts(allProducts);

        vm.setMessageTotalProducts("Số sản phẩm hiện có: " + totalProducts);

        model.addAttribute("vm",vm);
        return "admin/manage_product";
    }

    @GetMapping(path = "/manage_member")
    public String memberAdmin() {
        return "admin/manage_member";
    }

    @GetMapping(path = "/manage_order")
    public String orderAdmin() {
        return "admin/manage_order";
    }


    @GetMapping(path = "/manage_feedback")
    public String feedbackAdmin() {
        return "admin/manage_feedback";
    }

    @GetMapping(path = "/manage_subscribers")
    public String subscribersAdmin() {
        return "admin/manage_subscribers";
    }
}
