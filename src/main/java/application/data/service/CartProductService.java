package application.data.service;

import application.data.model.CartProduct;
import application.data.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductService {
    @Autowired
    private CartProductRepository cartProductRepository;

    public void addNewCartProduct (CartProduct cartProduct) {
        cartProductRepository.save(cartProduct);
    }

    public CartProduct findOne(int cartProductId) {
        return cartProductRepository.findOne(cartProductId);
    }

    public boolean updateCartProduct (CartProduct cartProduct) {
        try {
            cartProductRepository.save(cartProduct);
            return true;
        }catch (Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCartProduct(int cartProductId) {
        try {
            cartProductRepository.delete(cartProductId);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteListCartProducts (List<CartProduct> cartProductList) {
        try{
            cartProductRepository.delete(cartProductList);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
