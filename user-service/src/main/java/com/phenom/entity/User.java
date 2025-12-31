package com.phenom.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long userId;
    private String name;
    private String phone;

    private List<Contact> contacts=new ArrayList<>();

    public User() {}

    public User(List<Contact> contacts, String phone, String name, Long userId) {
        this.contacts = contacts;
        this.phone = phone;
        this.name = name;
        this.userId = userId;
    }

    public User(String phone, String name, Long userId) {
        this.phone = phone;
        this.name = name;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
