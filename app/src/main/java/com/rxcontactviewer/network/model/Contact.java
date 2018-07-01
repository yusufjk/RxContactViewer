package com.rxcontactviewer.network.model;

public class Contact {
    private String[] managers;
    private String[] addresses;
    private String[] phones;
    private String name;
    private String parent;
    private String companyName;

    public Contact(String[] managers, String[] addresses, String[] phones, String name, String companyName, String parent) {
        this.managers = managers;
        this.addresses = addresses;
        this.phones = phones;
        this.name = name;
        this.companyName = companyName;
        this.parent = parent;
    }

    public String[] getAddresses() {
        return addresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String[] getManagers() {
        return managers;
    }

    public void setManagers(String[] managers) {
        this.managers = managers;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "ClassPojo [managers = " + managers + ", parent = " + parent + ", companyName = " + companyName + "name = " + name + "addresses = " + addresses + "phones = " + phones + "]";
    }
}