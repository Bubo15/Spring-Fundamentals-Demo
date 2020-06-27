package com.shop.dealershop.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController{

    @GetMapping("/")
    public ModelAndView home(ModelAndView model, HttpSession session){
        model.setViewName("home");
        session.setAttribute("headerPage", "home");
        return model;
    }
}
