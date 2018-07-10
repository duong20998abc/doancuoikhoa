package application.viewmodel.productindex;

import application.model.ProductDetailModel;

import java.util.ArrayList;

public class ProductSearchVM {
    private ArrayList<ProductDetailModel> productDetailModels;
    private String keyWord;

    public ArrayList<ProductDetailModel> getProductDetailModels() {
        return productDetailModels;
    }

    public void setProductDetailModels(ArrayList<ProductDetailModel> productDetailModels) {
        this.productDetailModels = productDetailModels;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
