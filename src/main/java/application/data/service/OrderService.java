package application.data.service;

import application.data.model.Order;
import application.data.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public static final Logger logger = LogManager.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void addNewListOrders (List<Order> orders) {
        orderRepository.save(orders);
    }

    public List<Order> getListOrders() {
        try {
            return orderRepository.findAll();
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public boolean updateOrder(Order order) {
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteOrder(int orderId) {
        try {
            orderRepository.delete(orderId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public Order findOne(int OrderId) {
        return orderRepository.findOne(OrderId);
    }
}
