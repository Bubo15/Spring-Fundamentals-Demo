package com.shop.dealershop.service.services;

import com.shop.dealershop.service.model.OfferServiceModel;
import com.shop.dealershop.web.model.bindingModel.OfferCreateBindingModel;

import java.util.List;

public interface OfferService {

    OfferServiceModel addOffer(OfferCreateBindingModel offerCreateBindingModel);

    List<OfferServiceModel> getAllOffers();

    OfferServiceModel getById(long id);

    void deleteOfferById(long id);
}
