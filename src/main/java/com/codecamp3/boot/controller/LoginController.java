package com.codecamp3.boot.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.codecamp3.boot.entity.AppUser;
import com.codecamp3.boot.service.AppUserService;
import com.codecamp3.boot.util.WebUtil;

@Controller
public class LoginController {
    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtil.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        String userName = principal.getName();
        // TODO: uncomment เมื่อ appUserService พร้อมใช้งาน
        AppUser userInfo = appUserService.findByUserName(userName);
        model.addAttribute("userInfo", userInfo);
        return "userInfoPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(Model model,
            @RequestParam(value = "errorMessage", required = false) String errorMessage) {
        // TODO: เขียนเพิ่มตรงนี้
        model.addAttribute("errorMessage", errorMessage);
        return "registerPage";
    }

    @RequestMapping(value = "/registerValidation", method = RequestMethod.POST)
    public RedirectView registerValidation(RedirectAttributes attributes, 
            @RequestParam("username") String username,
            @RequestParam("password") String password, 
            @RequestParam("confirm_password") String confirmPassword,
            @RequestParam("firstname") String firstname, 
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email, 
            @RequestParam("confirm_email") String confirmEmail,
            @RequestParam("birth_day") String birthDay, 
            @RequestParam("birth_month") String birthMonth,
            @RequestParam("birth_year") String birthYear, 
            @RequestParam("gender") String gender) {

        String errorMessage = appUserService.registerAndValidation(username, password, confirmPassword, firstname,
                lastname, email, confirmEmail, birthDay, birthMonth, birthYear, gender);
        if (errorMessage.equals("")) {
            return new RedirectView("registerSuccess");
        } else {
            attributes.addAttribute("errorMessage", errorMessage);
            return new RedirectView("register"); // send data to URL register to display error
        }
    }
    // TODO: เพิ่มหน้า registerSuccess ตรงนี้ โดยโหลด template ชื่อ
    // registerSuccessfulPage.html มาแสดงผล (ไฟล์ registerSuccessfulPage.html ให้
    // Save as จาก logoutSuccessfulPage.html มาแก้ไข)
    @RequestMapping(value = "/registerSuccess", method = RequestMethod.GET)
    public String registerSuccess(Model model) {
        // TODO: เขียนเพิ่มตรงนี้
        return "registerSuccessfulPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtil.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }
}