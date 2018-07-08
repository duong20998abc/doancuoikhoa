package application.controller.web;

import application.data.model.Cart;
import application.data.model.CartProduct;
import application.data.model.Order;
import application.data.model.OrderProduct;
import application.data.service.CartProductService;
import application.data.service.CartService;
import application.data.service.OrderProductService;
import application.data.service.OrderService;
import application.viewmodel.cart.CartViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private OrderProductService productOrderService;

    @GetMapping("/cart")
    public String cart(Model model, HttpServletResponse response, HttpServletRequest request) {

        Cookie cookie[] = request.getCookies();

        int cartid=0;

        for (Cookie c : cookie){
            if(c.getName().equals("cartid")){
                cartid=Integer.parseInt(c.getValue());
            }
        }

        CartViewModel vm = new CartViewModel();
        Cart cart = cartService.findOne(cartid);


        int sum=0;

        for(CartProduct cartProduct : cart.getListCartProducts()){
            sum+=cartProduct.price();
        }

        cart.setPrice(sum);
        vm.setCart(cart);

        model.addAttribute("vm",vm);

        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout( Model model, HttpServletResponse response, HttpServletRequest request) {
        model.addAttribute("order",new Order());
        return "checkout";
}

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkOutPost (@Valid @ModelAttribute Order order, HttpServletRequest request, HttpServletResponse response){

        Cookie cookie[] = request.getCookies();

        int cartid =0;

        if(cookie!=null){
            for (Cookie c : cookie){
                if(c.getName().equals("cartid")){
                    cartid=Integer.parseInt(c.getValue());
                }
            }
        }

        if(cartid!=0) {
            Cart cart = cartService.findOne(cartid);
            if(cart.getUsername()!=null)    order.setUsername(cart.getUsername());
            order.setGuid(cart.getGuid());

            orderService.addNewOrder(order);

            List<OrderProduct> productOrders = new ArrayList<>();

            int sum=0;

            for(CartProduct cartProduct : cart.getListCartProducts()){
                OrderProduct productOrder = new OrderProduct();
                productOrder.setAmount(cartProduct.getAmount());
                productOrder.setPrice(cartProduct.getProduct().getPrice());
                productOrder.setProduct(cartProduct.getProduct());
                productOrder.setOrder(order);
                productOrders.add(productOrder);
                sum= sum + productOrder.getPrice();
            }

            order.setPrice(sum);

            orderService.addNewOrder(order);

            productOrderService.addNewListProducts(productOrders);

            cartProductService.deleteListCartProducts(cart.getListCartProducts());

            cartService.deleteCart(cart.getId());

            Cookie cookie1 = new Cookie("username",null);
            cookie1.setHttpOnly(true);
            cookie1.setMaxAge(0);
            response.addCookie(cookie1);


            Cookie cookie2 = new Cookie("cartid",null);
            cookie2.setHttpOnly(true);
            cookie2.setMaxAge(0);
            response.addCookie(cookie2);

            Cookie cookie3 = new Cookie("guid",null);
            cookie3.setHttpOnly(true);
            cookie3.setMaxAge(0);
            response.addCookie(cookie3);

        }

        return "redirect:/";
    }
}
