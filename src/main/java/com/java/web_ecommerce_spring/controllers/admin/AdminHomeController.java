package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.domain.Order;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.domain.User;
import com.java.web_ecommerce_spring.serviceImpls.OrderServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.ProductServiceImpl;
import com.java.web_ecommerce_spring.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminHomeController {
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    OrderServiceImpl orderService
;
    @GetMapping(value = "/admin/home")
    public ModelAndView index(Principal principal){
        ModelAndView mv = new ModelAndView("admin/index");
        String userName = principal.getName();
        List<Product> listC = productService.getAll();
        List<User> listU = userService.listCustomer();
        List<Order> listB = orderService.findAll();
        float sum = 0;
        for (Order order : listB) {
            sum = sum + order.getAmount();
        }
        int countC = listC.size();
        int countU = listU.size();
        int countB = listB.size();
        mv.addObject("countC",countC);
        mv.addObject("countU",countU);
        mv.addObject("countB",countB);
        mv.addObject("sum",sum);
        mv.addObject("userName",userName);
        return mv;
    }

}
