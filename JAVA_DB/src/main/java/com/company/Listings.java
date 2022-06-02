package com.company;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listings")
public class Listings {
    @Id
    public String id;
    public String nickname;
    public String title;
    public float price;
    public String city;
    public String contactName;
    public String phoneNumber;
    public String notes;
    public boolean report;
    public boolean sold;
    public boolean deleted;

    public Listings() {
    }

    public Listings(String id, String nickname, String title, Float price, String city, String contactName, String phoneNumber, String notes, boolean report, boolean sold, boolean deleted) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.price = price;
        this.city = city;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.report = report;
        this.sold = sold;
        this.deleted = deleted;
    }

    public Listings(String id, String nickname, String title, Float price, String city, String contactName, String phoneNumber, String notes) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.price = price;
        this.city = city;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.report = false;
        this.sold = false;
        this.deleted = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
