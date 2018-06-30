package com.rxcontactviewer.network.model;

import java.lang.reflect.Array;


public class Contact {
    String name, companyName, parent;
    Array phones;
    Array addresses;
    Array managers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Array getPhones() {
        return phones;
    }

    public void setPhones(Array phones) {
        this.phones = phones;
    }

    public Array getAddresses() {
        return addresses;
    }

    public void setAddresses(Array addresses) {
        this.addresses = addresses;
    }

    public Array getManagers() {
        return managers;
    }

    public void setManagers(Array managers) {
        this.managers = managers;
    }
}
