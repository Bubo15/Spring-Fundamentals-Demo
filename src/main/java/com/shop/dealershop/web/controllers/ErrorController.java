package com.shop.dealershop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @GetMapping("/error/unauthorized")
    public ModelAndView getErr(ModelAndView modelAndView){
        modelAndView.setViewName("unauthorized");
        return modelAndView;
    }
}
