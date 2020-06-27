package com.shop.dealershop.web.model.bindingModel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;

@ScriptAssert(lang = "javascript",
        script = "_this.rePassword !== null &&_this.password === _this.rePassword",
        reportOn = "rePassword",
        message = "Passwords don't match")
public class UserRegisterBindingModel {

    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String password;
    private String rePassword;

    public UserRegisterBindingModel() {
    }


    @Length(min = 2, message = "Username must be least 2 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2, message = "First name must be least 2 symbols")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 2, message = "Last name must be least 2 symbols")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull(message = "Img is required")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Length(min = 3, message = "Password must be least 3 symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
