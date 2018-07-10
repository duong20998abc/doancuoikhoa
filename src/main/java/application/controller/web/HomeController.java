package application.controller.web;

import application.constant.StatusRegisterUserEnum;
import application.data.model.*;
import application.data.service.*;
import application.model.*;
import application.viewmodel.admin.AdminVM;
import application.viewmodel.common.ProductVM;
import application.viewmodel.landing.BannerVM;
import application.viewmodel.landing.LandingVM;
import application.viewmodel.landing.MenuItemVM;
import application.viewmodel.user.UserVM;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping(path = "/")
public class HomeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private NewService newService;

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(path = "admin", method = RequestMethod.GET)
    public String admin(Model model, @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

        return "admin";
    }

    @GetMapping(path="/list-products/{catId}")
    public String listProducts(Model model, @PathVariable int catId) {

        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);
        ArrayList<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/10/may-chay-bo.jpg","Hah"));
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/10/may-chay-bo-phong-gym.jpg","Hah"));

        Category existedCategory = categoryService.findbyId(catId);

        try {
                ModelMapper modelMapper = new ModelMapper();

                    CategoryProductModel categoryProductModel = modelMapper.map(existedCategory,CategoryProductModel.class);
                    model.addAttribute("cat",categoryProductModel);
//                model.addAttribute("paginableItem",productService.getListProducts(pageSize,pageNumber));

        }catch (Exception e) {
            e.printStackTrace();
        }

        vm.setListBanners(listBanners);
        model.addAttribute("vm",vm);
        return "list-products";
    }

    @GetMapping(path = "/")
    public String index(Model model,  HttpServletResponse response,
                        @RequestHeader("User-Agent") String userAgent,
                        HttpServletRequest request,
                        final Principal principal ,
                        @RequestParam(value = "categoryId", required = false)
                                    String productCategoryId) {
        response.addCookie(new Cookie("current-page", "Cookie from Java code - Home Landing"));
        System.out.println("===========");
        System.out.println(userAgent);
        System.out.println("IP: " + request.getRemoteAddr());

        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);

        ArrayList<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/08/BANER-1-min.jpg","Hah"));
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/08/BANER-1-min.jpg","Hah"));
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/08/BANER-3.jpg","Hah"));

        ArrayList<MenuItemVM> listVtMenuItems = new ArrayList<>();
        listVtMenuItems.add(new MenuItemVM("DANH MỤC SẢN PHẨM","/"));
        listVtMenuItems.add(new MenuItemVM("Máy chạy bộ điện","/"));
        listVtMenuItems.add(new MenuItemVM("Xe đạp tập thể dục","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ thể hình","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ yoga- thẩm mỹ","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ bơi lội","/"));
        listVtMenuItems.add(new MenuItemVM("Giày thể thao","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ võ thuật","/"));

        List<Category> listCategories = categoryService.getListAllCategories();

        vm.setCategoryList(listCategories);

        String  username = SecurityContextHolder.getContext().getAuthentication().getName();

        User listUsers = userService.findUserByUsername(username);
        vm.setUser(listUsers);

        if(principal != null) {
            System.out.println(username);
            Cookie cookie[] = request.getCookies();

            int kt1= 0;
            int kt2 = 0;
            int kt3 = 0;

            String guid1="";

            for(Cookie c : cookie) {
                if(c.getName().equals("username")) {
                    kt1 = 1;
                }
                if(c.getName().equals("guid")) {
                    kt2 = 1;
                    guid1 = c.getValue();
                }
                if(c.getName().equals("cartid")) {
                    kt3 = 1;
                }

            }

            if(kt1 == 0) {
                Cookie cookie1 = new Cookie("username",username);
                response.addCookie(cookie1);

                List<Cart> carts = cartService.findByUserName(username);
                if(carts.size()> 0) {
                    String guid2 = carts.get(0).getGuid();
                    Cookie cookie2 = new Cookie("guid",guid2);
                    response.addCookie(cookie2);

                    Cookie cookie3 = new Cookie("cartid",Integer.toString(carts.get(0).getId()));
                    response.addCookie(cookie3);
                }else {
                    UUID uuid = UUID.randomUUID();
                    String guid = uuid.toString();

                    Cart cart = new Cart();
                    cart.setUsername(username);
                    cart.setGuid(guid);
                    cartService.addNewCart(cart);
                    System.out.println(cart.getUsername());
                    System.out.println(cart.getGuid());

                    Cookie cookie2 = new Cookie("guid",guid);
                    response.addCookie(cookie2);

                    Cookie cookie3 = new Cookie("cartid",Integer.toString(cart.getId()));
                    response.addCookie(cookie3);
                }
            }else {
                if(kt2 == 0) {
                    UUID uuid = UUID.randomUUID();
                    String guid = uuid.toString();

                    Cart cart = new Cart();
                    cart.setUsername(username);
                    cart.setGuid(guid);
                    cartService.addNewCart(cart);

                    Cookie cookie1 = new Cookie("guid",guid);
                    response.addCookie(cookie1);
                    Cookie cookie2 = new Cookie("cartid",Integer.toString(cart.getId()));
                    response.addCookie(cookie2);
                }
            }
        }

        int categoryId;
        if(productCategoryId != null) {
            categoryId = Integer.parseInt(productCategoryId);
        }else {
            categoryId = 1;
        }

        List<Product> productList = categoryService.findOne(categoryId).getProducts();

        vm.setProductList(productList);
        model.addAttribute("vm", vm);
        return "index";
    }

    @GetMapping(path = "/user/{userId}")
    public String memberProfile(Model model, @PathVariable int userId) {
        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);

        User existedUser = userService.findUserById(userId);
        try {
            ModelMapper modelMapper = new ModelMapper();

            UserDetailModel userDetailModel = modelMapper.map(existedUser,UserDetailModel.class);
            model.addAttribute("user",userDetailModel);
//                model.addAttribute("paginableItem",productServicse.getListProducts(pageSize,pageNumber));

        }catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("vm",vm);
        return "profile";
    }

    @GetMapping(path = "/news")
    public String news(Model model, @RequestParam(value = "pageNumber", required = false)Integer pageNumber){
        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);
        model.addAttribute("vm",vm);

        AdminVM adminVM = new AdminVM();
        ArrayList<New> news = newService.getAll();

        int pageSize = 5;
        if(pageNumber == null) {
            pageNumber = 1;
        }

        PaginableItemList<New> paginableItemList = newService.getListNews(pageSize,pageNumber - 1);
        List<New> listNews = paginableItemList.getListData();

        int totalPages = 0;
        if(paginableItemList.getTotalNews() % pageSize == 0) {
            totalPages = (int)(paginableItemList.getTotalNews() / pageSize);
        } else {
            totalPages = (int)(paginableItemList.getTotalNews() / pageSize) + 1;
        }

        adminVM.setTotalPagingItems(totalPages);
        adminVM.setCurrentPage(pageNumber);

        model.addAttribute("adminvm",adminVM);
        model.addAttribute("news",news);
        return "news";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);
        model.addAttribute("vm", vm);
        return "login";
    }

    @GetMapping(path = "/contact")
    public String contact(Model model){
        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);
        model.addAttribute("vm",vm);
        return "contact";
    }


}
