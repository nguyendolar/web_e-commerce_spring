package com.java.web_ecommerce_spring.controllers.customer;

import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.services.CategoryService;
import com.java.web_ecommerce_spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/public/cart")
public class CartController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("public/cart");
        mv.addObject("categories",categoryService.getAll());
        return mv;
    }
}
