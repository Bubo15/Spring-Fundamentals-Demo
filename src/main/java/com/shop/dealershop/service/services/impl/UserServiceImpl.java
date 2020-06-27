package com.shop.dealershop.service.services.impl;

import com.shop.dealershop.data.entities.Role;
import com.shop.dealershop.data.entities.User;
import com.shop.dealershop.data.entities.UserProfile;
import com.shop.dealershop.data.repositoies.UserRepository;
import com.shop.dealershop.service.model.UserRegisterServiceModel;
import com.shop.dealershop.service.model.UserServiceModel;
import com.shop.dealershop.service.services.UserService;
import com.shop.dealershop.web.model.bindingModel.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, HttpSession session) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.session = session;
    }

    @Override
    public Optional<UserRegisterServiceModel> register(UserRegisterBindingModel userModel) {// The check is in UserRegisterBindingModel with @ScriptAssert.....
//        if (!userModel.getPassword().equals(userModel.getRePassword())) {
//            return Optional.empty();
//        }

        if (this.userRepository.getByUsername(userModel.getUsername()) != null) {
            return Optional.empty();
        }

        User user = this.modelMapper.map(userModel, User.class);
        user.setPassword(this.passwordEncoder.encode(userModel.getPassword()));

        UserProfile userProfile = this.modelMapper.map(userModel, UserProfile.class);
        LocalDateTime date = LocalDateTime.now();
        userProfile.setCreated(date);
        userProfile.setOffers(new ArrayList<>());
        userProfile.setActive(true);

        user.setUserProfile(userProfile);

        Role role = new Role();
        if (userRepository.findAll().size() == 0) {
            role.setAuthority("ADMIN");
        } else {
            role.setAuthority("BUYER");
        }

        user.setAuthorities(new HashSet<>(Set.of(role)));

        this.userRepository.saveAndFlush(user);
        return Optional.of(this.modelMapper.map(userProfile, UserRegisterServiceModel.class));
    }

//    @Override
//    public UserDetails login(UserLoginBindingModel userModel) {
//        UserDetails user = this.loadUserByUsername(userModel.getUsername());
//
//        if (user == null) {
//            return null;
//        }
//
//         if(passwordEncoder.matches(user.getPassword(), userModel.getPassword())){
//             return null;
//         }
//
//        return user;
//    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel getUserByName(String username) {
        return this.modelMapper.map(this.userRepository.getByUsername(username), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = this.userRepository.getByUsername(s);
//        List<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);


         if (this.userRepository.getByUsername(s) == null){
             throw new UsernameNotFoundException("Bad credentials");
         }else {
             session.setAttribute("username", s);
             return this.userRepository.getByUsername(s);
         }
    }
}
