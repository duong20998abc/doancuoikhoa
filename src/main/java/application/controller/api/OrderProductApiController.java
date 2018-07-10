package application.controller.api;

import application.data.model.OrderProduct;
import application.data.service.OrderProductService;
import application.data.service.OrderService;
import application.data.service.ProductService;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.model.OrderProductDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-product")
public class OrderProductApiController {
    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order-product")
    public BaseApiResult createOrderProduct (@RequestBody OrderProductDataModel orderProductDataModel) {
        DataApiResult result = new DataApiResult();

        try {
            if(orderProductDataModel.getOrderid() != 0 && orderProductDataModel.getAmount() != 0 && orderProductDataModel.getProductid() != 0) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(orderService.findOne(orderProductDataModel.getOrderid()));
                orderProduct.setAmount(orderProductDataModel.getAmount());
                orderProduct.setProduct(productService.findOne(orderProductDataModel.getProductid()));
                orderProduct.setPrice(orderProduct.getProduct().getPrice());
                orderProductService.addNewOrderProduct(orderProduct);
                result.setSuccess(true);
                result.setMessage("Lưu thành công");
                result.setData(orderProduct.getId());
            }
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return  result;
    }

    @GetMapping("/detail/{orderProductId")
    public BaseApiResult OrderProductDetail(@PathVariable int orderProductId) {
        DataApiResult result = new DataApiResult();

        try {
            OrderProduct orderProduct = orderProductService.findOne(orderProductId);
            if(orderProduct == null) {
                result.setSuccess(false);
                result.setMessage("Không tìm thấy");
            }else {
                result.setSuccess(true);
                result.setData(orderProduct);
            }
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return  result;
    }

    @PostMapping("/update/{productOrderId}")
    public BaseApiResult updateProductOrder(@PathVariable int productOrderId, @RequestBody OrderProductDataModel orderProductDataModel) {
        DataApiResult result = new DataApiResult();

        try {
            if(orderProductDataModel.getOrderid() != 0 && orderProductDataModel.getProductid() != 0 &&
                    orderProductDataModel.getAmount()!=0) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setAmount(orderProductDataModel.getAmount());
                orderProduct.setProduct(productService.findOne(orderProductDataModel.getProductid()));
                orderProduct.setPrice(orderProduct.getProduct().getPrice());
                orderProductService.addNewOrderProduct(orderProduct);
                result.setSuccess(true);
                result.setMessage("Lưu thành công");
                result.setData(orderProduct.getId());
            }
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/delete/{productOrderId}")
    public BaseApiResult deleteProductImage(@PathVariable int productOrderId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(orderProductService.deleteOrderProduct(productOrderId)){
                result.setSuccess(true);
                result.setMessage("Delete product status successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
