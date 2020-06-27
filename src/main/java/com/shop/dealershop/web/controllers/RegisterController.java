package com.shop.dealershop.web.controllers;

import com.shop.dealershop.helper.Helper;
import com.shop.dealershop.service.model.UserRegisterServiceModel;
import com.shop.dealershop.service.services.UserService;
import com.shop.dealershop.web.model.bindingModel.UserRegisterBindingModel;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private static final String BINDING_RESULT_NAME = "org.springframework.validation.BindingResult.";
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView getRegister(HttpSession session, ModelAndView modelAndView, Model model) {
        Helper.Model.addAttributesAndView(new HashMap<>() {{
            put("user", new UserRegisterBindingModel());
        }}, modelAndView, "register");
        modelAndView.addAllObjects(model.asMap());

        session.setAttribute("headerPage", "notHome");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@Valid @ModelAttribute("user") UserRegisterBindingModel userRegisterBindingModel,
                                     BindingResult result,
                                     ModelAndView modelAndView,
                                     RedirectAttributes redirectAttributes) {//            Map<String, List<String>> errors = result
//                    .getFieldErrors()
//                    .stream()
//                    .collect(Collectors.groupingBy(k -> k.getField(),
//                            Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, toList())));

        if (Helper.Error.setViewAndIfHasErrRedirectAttr(modelAndView, result, redirectAttributes,
                new HashMap<>() {{
                    put(BINDING_RESULT_NAME + "user", result);
                    put("user", userRegisterBindingModel);
                }}, "/users/register", "/users/login")) {

        } else {
            Optional<UserRegisterServiceModel> user = this.userService.register(userRegisterBindingModel);

            if (!user.isPresent()) {
                Helper.Error.setViewAndIfHasErrRedirectAttr(modelAndView, result, redirectAttributes, new HashMap<>() {{
                    put("error", "This username's already exist");
                    put("user", userRegisterBindingModel);
                }}, "/users/register", "/users/login");
            }
        }

        return modelAndView;
    }
}
