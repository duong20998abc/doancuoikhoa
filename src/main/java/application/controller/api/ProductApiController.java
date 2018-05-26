package application.controller.api;

import application.constant.Constant;
import application.data.model.Category;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.*;
import application.viewmodel.common.ProductVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private String[] images = {
            "https://images-na.ssl-images-amazon.com/images/I/519rVW4jTIL._SL500_SS135_.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/51JJ0e-i2yL._SL500_SS135_.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/517Q0wwZUXL._SL500_SS135_.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/41odx4vtkyL._SL500_SS135_.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/41KCisxFTwL._SL500_SS135_.jpg"
    };

    @GetMapping("/detail/{productId}")
    public BaseApiResult detailProduct(@PathVariable int productId) {
        DataApiResult result = new DataApiResult();
        try{
            Product existProduct = productService.findOne(productId);
            if(existProduct == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                result.setSuccess(true);
                ModelMapper modelMapper = new ModelMapper();
                ProductDetailModel productDetailModel =
                        modelMapper.map(existProduct, ProductDetailModel.class);
                result.setData(productDetailModel);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/list-products/{categoryId}")
    public BaseApiResult listProductByCat(@PathVariable int categoryId) {
        DataApiResult result = new DataApiResult();
        try{
            Category existCategory = categoryService.findOne(categoryId);
            if(existCategory == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                result.setSuccess(true);
                ModelMapper modelMapper = new ModelMapper();
                ArrayList<ProductDetailModel> listProducts = new ArrayList<>();
                if(existCategory.getProducts() != null) {
                    for (Product p : existCategory.getProducts()) {
                        ProductDetailModel productDetailModel =
                                modelMapper.map(p, ProductDetailModel.class);
                        listProducts.add(productDetailModel);
                    }
                }
                result.setData(listProducts);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }


    @PostMapping("/fake-products")
    public BaseApiResult fakeProducts() {
        ArrayList<Product> listProducts= new ArrayList<>();
        Random random = new Random();
        BaseApiResult result = new BaseApiResult();

        for(int i = 1; i <= 10; ++i) {
            Product p = new Product();
            p.setCreatedDate(new Date());
            p.setName("Product " + i);
            p.setShortDesc("Description for product " + i);
            p.setImage(images[random.nextInt(images.length)]);
            p.setPrice(200000);
            p.setQuantity(1);
            p.setCategory(categoryService.getOne(1));
            listProducts.add(p);
        }

        productService.addNewListProducts(listProducts);
        result.setSuccess(true);
        result.setMessage("Done");
        return result;
    }

    @PostMapping("/create-product")
    public BaseApiResult createProduct(@RequestBody ProductDataModel product) {
        DataApiResult result = new DataApiResult();

        try {
            if(!"".equals(product.getName()) && !"".equals(product.getShortDesc()) && !"".equals(product.getImage())) {
                ModelMapper modelMapper = new ModelMapper();
                Product productEntity = modelMapper.map(product, Product.class);
                productEntity.setCategory(categoryService.getOne(product.getCategoryId()));
                productService.addNewProduct(productEntity);
                result.setSuccess(true);
                result.setMessage("Save product successfully: " + productEntity.getId());
                result.setData(productEntity.getId());
            } else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update-product/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductDataModel product) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(!"".equals(product.getName()) && !"".equals(product.getShortDesc())
                    && !"".equals(product.getImage())) {
                // check existed product
                Product existProduct = productService.findOne(productId);
                if(existProduct == null) {
                    result.setSuccess(false);
                    result.setMessage("Invalid model");
                } else {
                    existProduct.setImage(product.getImage());
                    existProduct.setName(product.getName());
                    existProduct.setCreatedDate(product.getCreatedDate());
                    existProduct.setShortDesc(product.getShortDesc());
                    existProduct.setPrice(product.getPrice());
                    existProduct.setQuantity(product.getQuantity());
                    existProduct.setCategory(categoryService.getOne(product.getCategoryId()));
                    productService.updateProduct(existProduct);
                    result.setSuccess(true);
                    result.setMessage("Update product successfully");
                }
            } else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/delete-product")
    public BaseApiResult deleteProduct(@RequestBody ProductDeleteDataModel product) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(productService.deleteProduct(product.getProductId())) {
                result.setSuccess(true);
                result.setMessage("Delete product successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
