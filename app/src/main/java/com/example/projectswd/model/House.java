package com.example.projectswd.model;

import java.io.Serializable;

public class House implements Serializable {
    private Integer id;
    private String houseName;
    private String Description;
    private Integer ownerId;
    private String profileImage;
    private Integer status;
    private Integer waterMeter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(Integer waterMeter) {
        this.waterMeter = waterMeter;
    }
}
