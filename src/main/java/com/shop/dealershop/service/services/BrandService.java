package com.shop.dealershop.service.services;


import com.shop.dealershop.service.model.BrandServerModel;
import com.shop.dealershop.web.model.bindingModel.BrandCreateBindingModel;

import java.util.List;

public interface BrandService {

    void addBrand(BrandCreateBindingModel bindingModel);

    List<BrandServerModel> getAllBrands();

    BrandServerModel getBrandByName(String name);
}
