package com.shop.dealershop.web.controllers;

import com.shop.dealershop.helper.Helper;
import com.shop.dealershop.service.services.BrandService;
import com.shop.dealershop.service.services.ModelService;
import com.shop.dealershop.web.model.bindingModel.ModelCreateBindingModel;
import com.shop.dealershop.web.model.viewModel.BrandViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/model")
public class ModelController {

    private static final String BINDING_RESULT_NAME = "org.springframework.validation.BindingResult.";

    private final ModelService modelService;
    private final BrandService brandService;

    @Autowired
    public ModelController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public ModelAndView getModel(ModelAndView modelAndView, Model model) {
        List<BrandViewModel> brands = (List<BrandViewModel>) Helper.Converter.convertListFromServiceModelToView(this.brandService.getAllBrands(), BrandViewModel.class);
        Helper.Model.addAttributesAndView(new HashMap<>() {{
            put("brands", brands);
            put("model", new ModelCreateBindingModel());
        }}, modelAndView, "model-add");

        modelAndView.addAllObjects(model.asMap());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addModel(@Valid @ModelAttribute("model") ModelCreateBindingModel modelCreateBindingModel,
                                 BindingResult result,
                                 ModelAndView modelAndView,
                                 RedirectAttributes redirectAttributes) {

        if (Helper.Error.setViewAndIfHasErrRedirectAttr(modelAndView, result, redirectAttributes, new HashMap<>() {{
            put("model", modelCreateBindingModel); put(BINDING_RESULT_NAME + "model", result);
        }}, "/model/add", "/")) {
            return modelAndView;
        }

        this.modelService.addModel(modelCreateBindingModel);
        return modelAndView;
    }
}
