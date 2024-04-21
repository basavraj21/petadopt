package com.project.petadopt;

public class Pet {

    String address,breed,category,description,gender,imageUrl,name,ownerEmail,  ownerUID,ownerName,sterlized,id;

    public Pet() {
    }

    public Pet(String address, String breed, String category, String description, String gender, String imageUrl, String name, String ownerEmail, String ownerUID, String ownerName, String sterlized, String id) {
        this.address = address;
        this.breed = breed;
        this.category = category;
        this.description = description;
        this.gender = gender;
        this.imageUrl = imageUrl;
        this.name = name;
        this.ownerEmail = ownerEmail;
        this.ownerUID = ownerUID;
        this.ownerName = ownerName;
        this.sterlized = sterlized;
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerUID() {
        return ownerUID;
    }

    public void setOwnerUID(String ownerUID) {
        this.ownerUID = ownerUID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSterlized() {
        return sterlized;
    }

    public void setSterlized(String sterlized) {
        this.sterlized = sterlized;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
