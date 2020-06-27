package com.shop.dealershop.web.model.viewModel;

import com.shop.dealershop.data.entities.Model;

import java.util.List;

public class BrandViewModel {

    private long id;
    private String name;
    private List<Model> models;

    public BrandViewModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
