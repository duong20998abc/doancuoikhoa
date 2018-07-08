package application.viewmodel.landing;

import application.data.model.Category;
import application.data.model.Product;
import application.data.model.User;
import application.model.UserDataModel;
import application.viewmodel.common.LayoutHeaderVM;
import application.viewmodel.common.LogoVM;
import application.viewmodel.common.ProductVM;

import java.util.ArrayList;
import java.util.List;

public class LandingVM extends LayoutHeaderVM {
    private ArrayList<MenuItemVM> listHrMenuItems;
    private ArrayList<MenuItemVM> listVtMenuItemsAside;
    private ArrayList<BannerVM> listBanners;
    private LogoVM logo;

    public LogoVM getLogo() {
        return logo;
    }

    public void setLogo(LogoVM logo) {
        this.logo = logo;
    }

    public ArrayList<MenuItemVM> getListHrMenuItems() {
        return listHrMenuItems;
    }

    public void setListHrMenuItems(ArrayList<MenuItemVM> listHrMenuItems) {
        this.listHrMenuItems = listHrMenuItems;
    }

    public ArrayList<MenuItemVM> getListVtMenuItemsAside() {
        return listVtMenuItemsAside;
    }

    public void setListVtMenuItemsAside(ArrayList<MenuItemVM> listVtMenuItemsAside) {
        this.listVtMenuItemsAside = listVtMenuItemsAside;
    }

    public ArrayList<BannerVM> getListBanners() {
        return listBanners;
    }

    public void setListBanners(ArrayList<BannerVM> listBanners) {
        this.listBanners = listBanners;
    }

    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
