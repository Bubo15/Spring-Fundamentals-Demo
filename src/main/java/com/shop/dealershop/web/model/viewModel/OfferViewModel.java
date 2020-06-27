package com.shop.dealershop.web.model.viewModel;

import com.shop.dealershop.data.entities.EngineType;
import com.shop.dealershop.data.entities.Model;

public class OfferViewModel {

    private long id;
    private Model model;
    private EngineType engineType;
    private int year;

    public OfferViewModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
