package com.contactManagement;

public class OrganizationContact extends Contact {

    private String organizationName;

    public OrganizationContact(String name, String organizationName) {
        super(name);
        this.organizationName = organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }
}