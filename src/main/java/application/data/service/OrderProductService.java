package application.data.service;

import application.data.model.OrderProduct;
import application.data.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;

    public void addNewOrderProduct (OrderProduct orderProduct){
        orderProductRepository.save(orderProduct);
    }

    @Transactional
    public void addNewListProducts(List<OrderProduct> listProductOrders) {
        orderProductRepository.save(listProductOrders);
    }

    public OrderProduct findOne (int orderProductId) {
        return orderProductRepository.findOne(orderProductId);
    }

    public boolean updateOrderProduct(OrderProduct orderProduct) {
        try {
            orderProductRepository.save(orderProduct);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOrderProduct(int orderProductId) {
        try {
            orderProductRepository.delete(orderProductId);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<OrderProduct> getListAllProductsOrdered() {
        try {
            return orderProductRepository.findAll();
        }catch (Exception e ) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
