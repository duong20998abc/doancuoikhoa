package application.data.service;

import application.data.model.ProductImage;
import application.data.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Transactional
    public void addNewListProductImages (List<ProductImage> productImages) {
        productImageRepository.save(productImages);
    }

    public void addNewProductImage (ProductImage productImage){
        productImageRepository.save(productImage);
    }

    public ProductImage findOne(int productImageid) {
        return productImageRepository.findOne(productImageid);
    }

    public boolean deleteProductImage(int productImageid){
        try {
            productImageRepository.delete(productImageid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProductImage(ProductImage productImage) {
        try {
            productImageRepository.save(productImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductImage> getListAllProductImages() {
        try {
            return productImageRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
