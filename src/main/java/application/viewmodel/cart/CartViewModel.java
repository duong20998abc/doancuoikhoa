package application.viewmodel.cart;

import application.data.model.Cart;
import application.data.model.CartProduct;

import java.util.List;

public class CartViewModel {
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
