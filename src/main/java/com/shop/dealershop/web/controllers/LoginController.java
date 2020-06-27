package com.shop.dealershop.web.controllers;

import com.shop.dealershop.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String getLogin(Model model, HttpSession session) {
        session.setAttribute("headerPage", "notHome");

//        if (model.getAttribute("username") == null) {
//            model.addAttribute("username", "");
//        }
        return "login";
    }


    @GetMapping("/login-err")
    @PreAuthorize("isAnonymous()")
    public String getLoginErr(Model model, HttpSession session) {
        session.setAttribute("headerPage", "notHome");
        model.addAttribute("errors", "Invalid username or password.");
        return "login";
    }

//    @PostMapping("/login")
//    public String postLogin(@ModelAttribute UserLoginBindingModel userModel,
//                            HttpSession session,
//                            RedirectAttributes redirectAttributes) {
//
//        UserDetails user = this.userService.login(userModel);
//
//        if (user == null) {
//            String errors = "Invalid username or password.";
//            redirectAttributes.addFlashAttribute("username", userModel.getUsername());
//            redirectAttributes.addFlashAttribute("errors", errors);
//            return "redirect:/users/login";
//        } else {
//            session.setAttribute("username", user.getUsername());
//
//        }
//
//        return "redirect:/";
//    }
}
