package com.shop.dealershop.service.services.impl;

import com.shop.dealershop.data.entities.Model;
import com.shop.dealershop.data.entities.Offer;
import com.shop.dealershop.data.entities.UserProfile;
import com.shop.dealershop.data.repositoies.OfferRepository;
import com.shop.dealershop.service.model.OfferServiceModel;
import com.shop.dealershop.service.services.ModelService;
import com.shop.dealershop.service.services.OfferService;
import com.shop.dealershop.service.services.UserService;
import com.shop.dealershop.web.model.bindingModel.OfferCreateBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ModelService modelService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, ModelService modelService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.modelService = modelService;
    }

    @Override
    @Transactional
    public OfferServiceModel addOffer(OfferCreateBindingModel offerCreateBindingModel) {

        UserProfile userProfile = this.modelMapper.map(this.userService.getUserByName(offerCreateBindingModel.getUserName()), UserProfile.class);
        Model model = this.modelMapper.map(this.modelService.getModelByName(offerCreateBindingModel.getModel()), Model.class);

        Offer offer = this.modelMapper.map(offerCreateBindingModel, Offer.class);
        offer.setSeller(userProfile);
        offer.setModel(model);
        LocalDateTime date = LocalDateTime.now();
        offer.setCreated(date);

        this.offerRepository.saveAndFlush(offer);

        return this.modelMapper.map(offer, OfferServiceModel.class);
    }

    @Override
    public List<OfferServiceModel> getAllOffers() {
        return this.offerRepository
                .findAll()
                .stream()
                .map(offer -> this.modelMapper.map(offer, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OfferServiceModel getById(long id) {
        Offer offer = this.offerRepository.getOfferById(id);
        return this.modelMapper.map(offer, OfferServiceModel.class);
    }

    @Override
    @Transactional
    public void deleteOfferById(long id) {
        this.offerRepository.deleteOfferById(id);
    }
}
