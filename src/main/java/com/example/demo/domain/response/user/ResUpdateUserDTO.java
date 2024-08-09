package com.example.demo.domain.response.user;

import java.time.Instant;

import com.example.demo.domain.response.user.ResCreateUserDTO.RoleUser;
import com.example.demo.util.constant.GenderEnum;

public class ResUpdateUserDTO {
    private long id;
    private String address;
    private GenderEnum gender;
    private int age;
    private String name;
    private Instant updatedAt;

    private RoleUser role;

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
