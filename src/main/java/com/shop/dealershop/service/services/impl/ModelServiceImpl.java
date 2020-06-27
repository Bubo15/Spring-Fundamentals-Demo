package com.shop.dealershop.service.services.impl;

import com.shop.dealershop.data.entities.Brand;
import com.shop.dealershop.data.entities.Model;
import com.shop.dealershop.data.repositoies.ModelRepository;
import com.shop.dealershop.service.model.ModelServiceModel;
import com.shop.dealershop.service.services.BrandService;
import com.shop.dealershop.service.services.ModelService;
import com.shop.dealershop.web.model.bindingModel.ModelCreateBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void addModel(ModelCreateBindingModel modelCreateBindingModel) {
        Brand brand = this.modelMapper.map(this.brandService.getBrandByName(modelCreateBindingModel.getBrand()), Brand.class);
        Model model = this.modelMapper.map(modelCreateBindingModel, Model.class);
        LocalDateTime date = LocalDateTime.now();

        model.setCreated(date);
        model.setBrand(brand);
        model.setOffers(new ArrayList<>());

        this.modelRepository.saveAndFlush(model);
    }

    @Override
    public List<ModelServiceModel> getAllModels() {
        return this.modelRepository
                .findAll()
                .stream()
                .map(model -> this.modelMapper.map(model, ModelServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ModelServiceModel getModelByName(String modelName) {
        Model model = this.modelRepository.findByName(modelName);

        if (model == null) {
            return null;
        }

        return this.modelMapper.map(model, ModelServiceModel.class);
    }
}
