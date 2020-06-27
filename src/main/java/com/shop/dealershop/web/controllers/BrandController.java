package com.shop.dealershop.web.controllers;

import com.shop.dealershop.helper.Helper;
import com.shop.dealershop.service.model.BrandServerModel;
import com.shop.dealershop.service.services.BrandService;
import com.shop.dealershop.web.model.bindingModel.BrandCreateBindingModel;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping("/brand")
public class BrandController {

    private static final String BINDING_RESULT_NAME = "org.springframework.validation.BindingResult.";

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public ModelAndView getBrand(ModelAndView modelAndView, Model model) {
        Helper.Model.addAttributesAndView(new HashMap<>() {{
            put("brand", new BrandCreateBindingModel());
        }}, modelAndView, "brand-add");
        modelAndView.addAllObjects(model.asMap());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addBrand(@Valid @ModelAttribute("brand") BrandCreateBindingModel brandCreateBindingModel,
                                 BindingResult result,
                                 ModelAndView modelAndView,
                                 RedirectAttributes attributes) {

        if (Helper.Error.setViewAndIfHasErrRedirectAttr(modelAndView, result, attributes, new HashMap<>() {{
            put("brand", brandCreateBindingModel); put(BINDING_RESULT_NAME + "brand", result);
        }}, "/brand/add", "/")) {
            return modelAndView;
        }

        this.brandService.addBrand(brandCreateBindingModel);
        return modelAndView;
    }
}
