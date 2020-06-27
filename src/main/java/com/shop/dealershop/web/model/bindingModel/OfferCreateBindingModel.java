package com.shop.dealershop.web.model.bindingModel;

import com.shop.dealershop.anotations.NotValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OfferCreateBindingModel {

    private String model;
    private String engine;
    private String category;
    private String transmission;
    private String username;
    private int year;
    private int mileage;
    private double price;
    private String imageUrl;
    private String description;

    public OfferCreateBindingModel() {
    }

    @NotValue(wrongValue = "Select model", message = "You have to choose value")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotValue(wrongValue = "Select Engine", message = "You have to choose value")
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @NotValue(wrongValue = "Select Category", message = "You have to choose value")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NotValue(wrongValue = "Select Transmission", message = "You have to choose value")
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @NotValue(wrongValue = "Select UserProfile", message = "You have to choose value")
    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Min(value = 1, message = "Year must be valid")
    @Max(value = 2020, message = "Year must be valid")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Min(value = 0, message = "Mileage must be positive number")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Min(value = 0, message = "Price must be positive number")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Length(min = 5, message = "Description must be least 5 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
