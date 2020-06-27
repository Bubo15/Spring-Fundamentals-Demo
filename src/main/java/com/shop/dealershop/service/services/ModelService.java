package com.shop.dealershop.service.services;

import com.shop.dealershop.service.model.ModelServiceModel;
import com.shop.dealershop.web.model.bindingModel.ModelCreateBindingModel;

import java.util.List;

public interface ModelService {

    void addModel(ModelCreateBindingModel modelCreateBindingModel);

    List<ModelServiceModel> getAllModels();

    ModelServiceModel getModelByName(String model);
}
