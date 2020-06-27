package com.shop.dealershop.service.services;

import com.shop.dealershop.service.model.UserLoginServiceModel;
import com.shop.dealershop.service.model.UserRegisterServiceModel;
import com.shop.dealershop.service.model.UserServiceModel;
import com.shop.dealershop.web.model.bindingModel.UserLoginBindingModel;
import com.shop.dealershop.web.model.bindingModel.UserRegisterBindingModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<UserRegisterServiceModel> register(UserRegisterBindingModel userModel);

   // UserDetails login(UserLoginBindingModel userModel);

    List<UserServiceModel> getAllUsers();

    UserServiceModel getUserByName(String seller);
}
