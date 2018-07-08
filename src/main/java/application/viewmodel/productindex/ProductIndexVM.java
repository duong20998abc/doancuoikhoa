package application.viewmodel.productindex;

import application.data.model.User;
import application.model.ProductDetailModel;
import application.viewmodel.common.LayoutHeaderVM;

public class ProductIndexVM extends LayoutHeaderVM {

    private ProductDetailModel info;

    public ProductDetailModel getInfo() {
        return info;
    }

    public void setInfo(ProductDetailModel info) {
        this.info = info;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
