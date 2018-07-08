package application.controller.api;

import application.data.model.Order;
import application.data.service.OrderService;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.model.OrderDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public BaseApiResult createOrder(@RequestBody OrderDataModel orderDataModel) {
        DataApiResult result = new DataApiResult();
        try {
            Order order = new Order();
            order.setAddress(orderDataModel.getAddress());
            order.setName(orderDataModel.getName());
            order.setEmail(orderDataModel.getEmail());
            order.setPhone(orderDataModel.getPhone());
            orderService.addNewOrder(order);
            result.setData(order.getId());
            result.setSuccess(true);
            result.setMessage("Lưu thành công");
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return  result;
    }
}
