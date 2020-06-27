package com.shop.dealershop.service.services.impl;

import com.shop.dealershop.data.entities.Brand;
import com.shop.dealershop.data.repositoies.BrandRepository;
import com.shop.dealershop.service.model.BrandServerModel;
import com.shop.dealershop.service.services.BrandService;
import com.shop.dealershop.web.model.bindingModel.BrandCreateBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addBrand(BrandCreateBindingModel bindingModel) {
        Brand brand = this.modelMapper.map(bindingModel, Brand.class);

        LocalDateTime date = LocalDateTime.now();
        brand.setCreated(date);
        brand.setModels(new ArrayList<>());

        this.brandRepository.saveAndFlush(brand);
    }

    @Override
    public List<BrandServerModel> getAllBrands() {
        return this.brandRepository
                .findAll()
                .stream()
                .map(brand -> this.modelMapper.map(brand, BrandServerModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandServerModel getBrandByName(String name) {
        Brand brand = this.brandRepository.getByName(name);

        if (brand == null){
            return null;
        }

        return this.modelMapper.map(brand, BrandServerModel.class);
    }
}
