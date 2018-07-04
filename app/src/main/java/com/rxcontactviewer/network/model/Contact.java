package com.rxcontactviewer.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Contact {
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("managers")
    @Expose
    private List<String> managers = null;
    @SerializedName("phones")
    @Expose
    private List<String> phones = null;
    @SerializedName("addresses")
    @Expose
    private List<String> addresses = null;
    @SerializedName("name")
    @Expose
    private String name;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<String> getManagers() {
        return managers;
    }

    public void setManagers(List<String> managers) {
        this.managers = managers;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
