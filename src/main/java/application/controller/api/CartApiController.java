package application.controller.api;

import application.data.model.Cart;
import application.data.service.CartService;
import application.model.BaseApiResult;
import application.model.CartDataModel;
import application.model.DataApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {
    @Autowired
    private CartService cartService;

    @PostMapping("/create-cart")
    public BaseApiResult createCart(@RequestBody CartDataModel cartDataModel) {
        DataApiResult result = new DataApiResult();
        try {
            Cart cart = new Cart();
            cart.setGuid(cartDataModel.getGuid());
            if (cartDataModel.getUsername() != null) {
                cart.setUsername(cartDataModel.getUsername());
            }
            cart.setCreatedDate(cartDataModel.getCreatedDate());
            cartService.addNewCart(cart);
            result.setData(cart.getId());
            result.setMessage("Lưu giỏ hàng thành công");
            result.setSuccess(true);
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return  result;
    }

    @GetMapping("/detail/{cartid}")
    public BaseApiResult cartDetail(@PathVariable int cartid) {
        DataApiResult result = new DataApiResult();
        try {
            Cart cart = cartService.findOne(cartid);
            if (cart == null) {
                result.setSuccess(false);
                result.setMessage("Giỏ hàng không tồn tại!!!");
            }else {
                result.setSuccess(true);
                result.setData(cart);
            }
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/delete/{cartid}")
    public BaseApiResult deleteCart (@PathVariable int cartid) {
        BaseApiResult result = new BaseApiResult();
        try {
            if(cartService.deleteCart(cartid)) {
                result.setSuccess(true);
                result.setMessage("Xoá giỏ hàng thành công");
            }
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return  result;
    }
}
