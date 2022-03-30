package com.java.web_ecommerce_spring.controllers.customer;

import com.java.web_ecommerce_spring.constans.CommonConstants;
import com.java.web_ecommerce_spring.domain.Product;
import com.java.web_ecommerce_spring.domain.Role;
import com.java.web_ecommerce_spring.domain.User;
import com.java.web_ecommerce_spring.services.RoleService;
import com.java.web_ecommerce_spring.services.UserService;
import com.java.web_ecommerce_spring.utils.EncrytedPasswordUtils;
import com.java.web_ecommerce_spring.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("public/user")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    public JavaMailSenderImpl javaMailSenderImpl;

    @Autowired
    RoleService roleService;


    @PostMapping("/register")
    public @ResponseBody String register(HttpServletRequest request){
        String fullName = request.getParameter("fullName");
        String userName = request.getParameter("userName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        System.out.println(password);
        System.out.println(rePassword);
        if (!password.equalsIgnoreCase(rePassword)){
            return "false_repass";
        }else {
            User objCheckEmail = userService.findUserByEmail(email);
            if (Objects.nonNull(objCheckEmail)){
                return "exist";
            } else {
                User user = new User();
                Role role = roleService.findRoleById(2);
                String passwordMd5 = EncrytedPasswordUtils.md5(password);
                user.setEmail(email);
                user.setEnable(0);
                user.setFullName(fullName);
                user.setPassword(passwordMd5);
                user.setPhoneNumber(phoneNumber);
                user.setRole(role);
                String link = "http://localhost:8080/public/user/verify/" + email;
                String html = "<div  class=\"container-fluid\" style=\"text-align: center\">\n" +
                        "    <p style=\"font-size: 20px;font-weight: bold;color: #aaa;margin-top: 10px\">Confirm email login</p>\n" +
                        "    <div style=\"width: 600px;height: 400px;border-radius: 5px;\n" +
                        "    box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px;margin: 20px auto;padding: 15px\">\n" +
                        "        <p style=\"line-height: 35px;font-size: 16px\">Xin chào, <span style=\"font-weight: bold;color: black\" >"+user.getFullName()+"</span><br>\n" +
                        "        <div style=\"height: 50px ;width: 100px;background-color: red;\n" +
                        "            border-radius: 5px;line-height:50px;padding-left:50px;margin: 10px auto;display: flex\">\n" +
                        "            <a href="+link+" style=\"color: white;text-decoration: none\">Confirm</a>\n" +
                        "        </div>\n" +
                        "\n" +
                        "        <p>___</p>\n" +
                        "            - Phone number:<span style=\"color:#5f80ec\">(024) 6680 5588</span><br>\n" +
                        "            - Email: <a href=\"#\" style=\"color:#5f80ec\"> dealdevice@gmail.vn</a>\n" +
                        "    </div>\n" +
                        "</div>";
                try {
                    MailUtil.sendHtmlMail(this.javaMailSenderImpl,email,"Bấm vào đường dẫn để xác thực tài khoản",html);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                userService.save(user);
                return "true";
            }
        }
    }

    @PostMapping("/login")
    public @ResponseBody String login(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordMd5 = EncrytedPasswordUtils.md5(password);
        User user = userService.findUserByEmailAndPassword(email,passwordMd5);
        if (!Objects.nonNull(user)){
            return "false_user";
        } else {
            if (user.getEnable() == 0){
                return  "false_verify";
            } else {
                HttpSession session = request.getSession();
                session.setAttribute(CommonConstants.SESSION_USER, user);
                return  "true";
            }
        }
    }

    @GetMapping("/verify/{email}")
    public ModelAndView verify(@PathVariable String email, RedirectAttributes rd ){
        User user = userService.findUserByEmail(email);
        user.setEnable(1);
        userService.save(user);
        ModelAndView mv = new ModelAndView("redirect:/public/home");
        rd.addFlashAttribute(CommonConstants.VERIFY, "verify_success");
        return mv;
    }

    @GetMapping(value = "/logout")
    public ModelAndView getLogout(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:/public/home");
        HttpSession session = request.getSession(false);
        session.invalidate();
        return mv;
    }
}
