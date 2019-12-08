package com.example.projectswd.model;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private Role role;
    private String email;
    private House house;
    private Integer creator;
    private String profileImage;
    private Date dateOfBirth;
    private String idNumber;
    private Integer gender;
    private String fullName;
    private Date createDate;
    private Date lastModified;
    private Integer status;
    private BigDecimal money;
public User(){

}

    public User(String idNumber, String fullName) {
        this.idNumber = idNumber;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int id, String username, String password, Role role, House house, Integer creator, String profileImage, Date dateOfBirth, String idNumber, Integer gender, String fullName, Date createDate, Date lastModified, Integer status, BigDecimal money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.house = house;
        this.creator = creator;
        this.profileImage = profileImage;
        this.dateOfBirth = dateOfBirth;
        this.idNumber = idNumber;
        this.gender = gender;
        this.fullName = fullName;
        this.createDate = createDate;
        this.lastModified = lastModified;
        this.status = status;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", house=" + house +
                ", creator=" + creator +
                ", profileImage='" + profileImage + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", idNumber='" + idNumber + '\'' +
                ", gender=" + gender +
                ", fullName='" + fullName + '\'' +
                ", createDate=" + createDate +
                ", lastModified=" + lastModified +
                ", status=" + status +
                ", money=" + money +
                '}';
    }
}
