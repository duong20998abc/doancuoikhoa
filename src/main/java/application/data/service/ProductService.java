package application.data.service;

import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void addNewListProducts(List<Product> listProducts) {
        productRepository.save(listProducts);
    }

    public long getTotalProducts() {
        return productRepository.getTotalProducts();
    }

//    public PaginableItemList<Product> getListProducts(int pageSize, int pageNumber) {
//        PaginableItemList<Product> paginableItemList = new PaginableItemList<>();
//        paginableItemList.setPageSize(pageSize);
//        paginableItemList.setPageNumber(pageNumber);
//
//        Page<Product> pages = productRepository.findAll(new PageRequest(pageNumber,pageSize));
//        paginableItemList.setTotalProducts(pages.getTotalElements());
//        paginableItemList.setListData(pages.getContent());
//        return paginableItemList;
//    }

    public Product findOne(int productid) {
        return productRepository.findOne(productid);
    }

    public boolean updateProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteProduct(int productid) {
        try {
            productRepository.delete(productid);
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public ArrayList<Product> getAll() {
        return productRepository.getAllProducts();
    }

    public ArrayList<Product> getListProductsByCategory(int categoryid) {
        return productRepository.getProductsByCategory(categoryid);

    }

    public Product findByName(String name){
        return productRepository.findByName(name);
    }

    public ArrayList<Product> findByNameContaining(String name){
        return productRepository.findByNameContaining(name);
    }

}
