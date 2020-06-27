package com.shop.dealershop.service.model;

import com.shop.dealershop.data.entities.Model;

import java.util.List;

public class BrandServerModel {

    private String name;
    private List<Model> models;

    public BrandServerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
