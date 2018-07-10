package application.viewmodel.admin;

import application.data.model.Category;
import application.data.model.New;
import application.data.model.Product;
import application.model.CategoryDataModel;
import application.viewmodel.common.ProductVM;

import java.util.ArrayList;
import java.util.List;

public class AdminVM {
    private String messageTotalProducts;
    private ArrayList<Product> listAllProducts;
    private ArrayList<ProductVM> listPagingProducts;
    private int totalPagingItems;
    private int currentPage;
    private List<Category> listCategories;
    private ArrayList<CategoryDataModel> listCategoryDataModels;
    private List<New> listNews;

    public String getMessageTotalProducts() {
        return messageTotalProducts;
    }
    public void setMessageTotalProducts(String messageTotalProducts) {
        this.messageTotalProducts = messageTotalProducts;
    }

    public ArrayList<ProductVM> getListPagingProducts() {
        return listPagingProducts;
    }

    public void setListPagingProducts(ArrayList<ProductVM> listPagingProducts) {
        this.listPagingProducts = listPagingProducts;
    }

    public int getTotalPagingItems() {
        return totalPagingItems;
    }

    public void setTotalPagingItems(int totalPagingProducts) {
        this.totalPagingItems = totalPagingProducts;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Category> getListCategories() {
        return listCategories;
    }

    public void setListCategories(List<Category> listCategories) {
        this.listCategories = listCategories;
    }

    public ArrayList<Product> getListAllProducts() {
        return listAllProducts;
    }

    public void setListAllProducts(ArrayList<Product> listAllProducts) {
        this.listAllProducts = listAllProducts;
    }

    public List<New> getListNews() {
        return listNews;
    }

    public void setListNews(List<New> listNews) {
        this.listNews = listNews;
    }

    public ArrayList<CategoryDataModel> getListCategoryDataModels() {
        return listCategoryDataModels;
    }

    public void setListCategoryDataModels(ArrayList<CategoryDataModel> listCategoryDataModels) {
        this.listCategoryDataModels = listCategoryDataModels;
    }
}
