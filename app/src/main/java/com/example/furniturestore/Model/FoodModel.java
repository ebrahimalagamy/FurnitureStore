package com.example.furniturestore.Model;

import java.util.List;

public class FoodModel
{
    private String name,image,id,description;
    private Long price;
    private List<AddonModel> addonModels;
    private List<SizeModel> sizeModels;

    public FoodModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<AddonModel> getAddonModels() {
        return addonModels;
    }

    public void setAddonModels(List<AddonModel> addonModels) {
        this.addonModels = addonModels;
    }

    public List<SizeModel> getSizeModels() {
        return sizeModels;
    }

    public void setSizeModels(List<SizeModel> sizeModels) {
        this.sizeModels = sizeModels;
    }
}
