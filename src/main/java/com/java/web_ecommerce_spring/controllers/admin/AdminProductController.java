package com.java.web_ecommerce_spring.controllers.admin;

import com.java.web_ecommerce_spring.constans.CommonConstants;
import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.services.CategoryService;
import com.java.web_ecommerce_spring.services.ProductService;
import com.java.web_ecommerce_spring.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping("admin")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/product")
    public ModelAndView index(Principal principal){
        ModelAndView mv = new ModelAndView("admin/product");
        mv.addObject("products" ,productService.getAll());
        mv.addObject("categorys" ,categoryService.getAll());
        String userName = principal.getName();
        mv.addObject("userName",userName);
        return mv;
    }

    @PostMapping("/product/add")
    public ModelAndView addItem(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file,
                          @RequestParam("categoryId") int categoryId, HttpServletRequest request, Model model, RedirectAttributes rd) throws IOException {
        ModelAndView mv = new ModelAndView("redirect:/admin/product");
        String fileNames = "";
        if (file != null) {
            fileNames = FileUtil.upload(file, request);
        }
        product.setImage(fileNames);
        product.setCreatedAt(java.time.LocalDate.now());

        Category category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);

        Product existItems = productService.getProductByName(product.getName());
        if (Objects.isNull(existItems))
        {
            Product itemsObj = productService.save(product);
            if (Objects.nonNull(itemsObj))
            {
                rd.addFlashAttribute(CommonConstants.MSG, "1");
            } else {
                rd.addFlashAttribute(CommonConstants.MSG, "2");
            }
        } else {
            rd.addFlashAttribute(CommonConstants.MSG, "2");
        }
        return mv;
    }

    @GetMapping("/product/delete/{id}")
    public ModelAndView delete(@PathVariable int id,RedirectAttributes rd,HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin/product");
        Product product = productService.getProductById(id);
        try {
            FileUtil.delete(product.getImage(),request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        productService.delete(id);
        rd.addFlashAttribute(CommonConstants.MSG, "1");
        return mv;
    }

    @PostMapping("/product/update/{productId}")
    public ModelAndView updateStore(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file,
                              @RequestParam("categoryId") int categoryId ,@PathVariable int productId,
                              HttpServletRequest request, Model model, RedirectAttributes rd) throws IOException {
        ModelAndView mv = new ModelAndView("redirect:/admin/product");
        Category category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);

        HttpSession session = request.getSession();
        product.setId(productId);
        String fileNames = "";
        if (!file.isEmpty()) {
            fileNames = FileUtil.upload(file, request);
        } else {
            fileNames = productService.getProductById(productId).getImage();
        }
        product.setImage(fileNames);

        String nameItemCurrent = productService.getProductById(productId).getName();

        if (nameItemCurrent.equalsIgnoreCase(product.getName())) {
            Product itemsObj = productService.save(product);
            if (Objects.nonNull(itemsObj))
            {
                rd.addFlashAttribute(CommonConstants.MSG, "1");
                return mv;
            } else {
                rd.addFlashAttribute(CommonConstants.MSG, "2");
                return mv;
            }
        } else {
            Product checkExist = productService.getProductByName(product.getName());
            if (Objects.isNull(checkExist)) {
                Product itemsObj = productService.save(product);
                if (Objects.nonNull(itemsObj))
                {
                    rd.addFlashAttribute(CommonConstants.MSG, "1");
                    return mv;
                } else {
                    rd.addFlashAttribute(CommonConstants.MSG, "2");
                    return mv;
                }
            } else {
                rd.addFlashAttribute(CommonConstants.MSG, "2");
                return mv;
            }

        }


    }
}
