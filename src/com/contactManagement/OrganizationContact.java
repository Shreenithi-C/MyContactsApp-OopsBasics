package com.contactManagement;

public class OrganizationContact extends Contact {

    private String organizationName;

    public OrganizationContact(String name, String organizationName) {
        super(name);
        this.organizationName = organizationName;
    }
    
    public OrganizationContact(OrganizationContact other) {
        super(other);
        this.organizationName = other.organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }
    
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}