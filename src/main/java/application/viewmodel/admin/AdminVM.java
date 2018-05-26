package application.viewmodel.admin;

import application.model.CategoryDataModel;
import application.viewmodel.common.ProductVM;

import java.util.ArrayList;

public class AdminVM {
    private String messageTotalProducts;
    private ArrayList<ProductVM> listPagingProducts;
    private int totalPagingItems;
    private int currentPage;
    private ArrayList<CategoryDataModel> listCategories;

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

    public ArrayList<CategoryDataModel> getListCategories() {
        return listCategories;
    }

    public void setListCategories(ArrayList<CategoryDataModel> listCategories) {
        this.listCategories = listCategories;
    }


}
