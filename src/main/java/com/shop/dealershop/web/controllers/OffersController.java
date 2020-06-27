package com.shop.dealershop.web.controllers;

import com.shop.dealershop.helper.Helper;
import com.shop.dealershop.service.services.ModelService;
import com.shop.dealershop.service.services.OfferService;
import com.shop.dealershop.service.services.UserService;
import com.shop.dealershop.web.model.bindingModel.OfferCreateBindingModel;
import com.shop.dealershop.web.model.viewModel.ModelViewModel;
import com.shop.dealershop.web.model.viewModel.OfferDetailsViewModel;
import com.shop.dealershop.web.model.viewModel.OfferViewModel;
import com.shop.dealershop.web.model.viewModel.UserViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private static final String BINDING_RESULT_NAME = "org.springframework.validation.BindingResult.";

    private final OfferService offerService;
    private final UserService userService;
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @Autowired
    public OffersController(OfferService offerService, UserService userService, ModelService modelService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.userService = userService;
        this.modelService = modelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ModelAndView getAllOffers(ModelAndView modelAndView) {
        List<OfferViewModel> offerViewModels = (List<OfferViewModel>) Helper.Converter.convertListFromServiceModelToView(this.offerService.getAllOffers(), OfferViewModel.class);
        Helper.Model.addAttributesAndView(new HashMap<>() {{
            put("offers", offerViewModels);
        }}, modelAndView, "all");
        return modelAndView;
    }

    @GetMapping("/car/{id}")
    public ModelAndView details(@PathVariable long id, ModelAndView modelAndView) {
        OfferDetailsViewModel offerDetailsViewModel = this.modelMapper.map(this.offerService.getById(id), OfferDetailsViewModel.class);
        Helper.Model.addAttributesAndView(new HashMap<>() {{
            put("offer", offerDetailsViewModel);
        }}, modelAndView, "details");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getOffer(ModelAndView modelAndView, Model model) {
        List<ModelViewModel> modelViewModels = (List<ModelViewModel>) Helper.Converter.convertListFromServiceModelToView(this.modelService.getAllModels(), ModelViewModel.class);
        List<UserViewModel> userViewModels = (List<UserViewModel>) Helper.Converter.convertListFromServiceModelToView(this.userService.getAllUsers(), UserViewModel.class);

        Helper.Model.addAttributesAndView(new HashMap<>() {{
            put("models", modelViewModels);
            put("users", userViewModels);
            put("offer", new OfferCreateBindingModel());
        }}, modelAndView, "offer-add");

        modelAndView.addAllObjects(model.asMap());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addOffer(@Valid @ModelAttribute("offer") OfferCreateBindingModel offerCreateBindingModel,
                                 BindingResult result,
                                 ModelAndView modelAndView,
                                 RedirectAttributes attributes) {

        if (Helper.Error.setViewAndIfHasErrRedirectAttr(modelAndView, result, attributes, new HashMap<>() {{
            put("offer", offerCreateBindingModel);put(BINDING_RESULT_NAME + "offer", result);
        }}, "/offers/add", "/")) {

            return modelAndView;
        }

        this.offerService.addOffer(offerCreateBindingModel);
        return modelAndView;
    }

    @GetMapping("/car/delete/{id}")
    public String delete(@PathVariable long id) {
        this.offerService.deleteOfferById(id);
        return "redirect:/offers";
    }
}
