package com.shop.dealershop.web.model.bindingModel;

import com.shop.dealershop.anotations.IsExistModel;
import com.shop.dealershop.anotations.NotValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ModelCreateBindingModel {

    private String brand;
    private String category;
    private String name;
    private int startYear;
    private int endYear;
    private String imageUrl;

    public ModelCreateBindingModel() {
    }

    @NotValue(wrongValue = "Select Brand", message = "You have to chose value")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @NotValue(wrongValue = "Select Category", message = "You have to chose value")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NotEmpty(message = "Can't be empty")
    @IsExistModel(message = "This Model Already exist")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 1, message = "Start Year must be valid")
    @Max(value = 2020, message = "Start Year must be valid")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Min(value = 1, message = "End Year must be valid")
    @Max(value = 2020, message = "End Year must be valid")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
