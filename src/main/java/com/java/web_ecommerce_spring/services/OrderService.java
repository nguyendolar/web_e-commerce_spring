package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> findAll();
    Order findOrderById(int id);
    int update(int status, int id);
    List<OrderDetail> listOd(int order_id);
<<<<<<< Updated upstream
    List<Order> getAmount(int month);
=======
    Order save(Order order);
>>>>>>> Stashed changes
}
