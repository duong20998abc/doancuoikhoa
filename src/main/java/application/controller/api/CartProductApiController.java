package application.controller.api;

import application.data.model.CartProduct;
import application.data.service.CartProductService;
import application.data.service.CartService;
import application.data.service.ProductService;
import application.model.BaseApiResult;
import application.model.CartProductDataModel;
import application.model.DataApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-product")
public class CartProductApiController {
    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create-cart-product")
    public BaseApiResult createCartProduct(@RequestBody CartProductDataModel cartProductDataModel) {
        DataApiResult result = new DataApiResult();
        try {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setAmount(cartProductDataModel.getAmount());
            cartProduct.setCart(cartService.findOne(cartProductDataModel.getCartId()));
            cartProduct.setProduct(productService.findOne(cartProductDataModel.getProductId()));
            cartProductService.addNewCartProduct(cartProduct);
            result.setData(cartProduct.getId());
            result.setMessage("Lưu thành công");
            result.setSuccess(true);
        }catch (Exception e ) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{cartproductId}")
    public BaseApiResult CartProductDetail(@PathVariable int cartproductId) {
        DataApiResult result = new DataApiResult();
        try {
            CartProduct cartProduct = cartProductService.findOne(cartproductId);
            CartProductDataModel cartProductDataModel = new CartProductDataModel();
            cartProductDataModel.setAmount(cartProduct.getAmount());
            cartProductDataModel.setCartId(cartProduct.getCart().getId());
            cartProductDataModel.setId(cartProduct.getId());
            cartProductDataModel.setProductId(cartProduct.getProduct().getId());

            if (cartProduct == null) {
                result.setSuccess(false);
                result.setMessage("Không tìm thấy");
            }else {
                result.setSuccess(true);
                result.setData(cartProductDataModel);
            }
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{cartProductId}")
    public BaseApiResult UpdateCartProduct(@PathVariable int cartProductId,
                                           @RequestBody CartProductDataModel cartProductDataModel) {
        DataApiResult result = new DataApiResult();
        try {
            CartProduct cartProduct = cartProductService.findOne(cartProductId);
            cartProduct.setAmount(cartProductDataModel.getAmount());
            cartProduct.setCart(cartService.findOne(cartProductDataModel.getCartId()));
            cartProduct.setProduct(productService.findOne(cartProductDataModel.getProductId()));
            cartProductService.addNewCartProduct(cartProduct);
            result.setSuccess(true);
            result.setMessage("Cập nhật thành công");
            result.setData(cartProduct);
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update-amount/{cartProductId}")
    public BaseApiResult updateAmount(@PathVariable int cartProductId, @RequestBody CartProductDataModel cartProductDataModel){
        DataApiResult result = new DataApiResult();

        try {
            CartProduct cartProduct = cartProductService.findOne(cartProductId);
            cartProduct.setAmount(cartProductDataModel.getAmount());
            cartProductService.addNewCartProduct(cartProduct);
            result.setSuccess(true);
            result.setMessage("Update amount cart product successfully: " + cartProduct.getId());
            result.setData(cartProduct);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/delete/{cartProductId}")
    public BaseApiResult deleteMaterial(@PathVariable int cartProductId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(cartProductService.deleteCartProduct(cartProductId)){
                result.setSuccess(true);
                result.setMessage("Xoá thành công");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
