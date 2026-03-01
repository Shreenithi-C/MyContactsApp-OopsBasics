package com.tagManagement;

import java.util.Objects;

public class Tag {

    private String name;

    public Tag(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tag name cannot be empty");
        }

        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    // Prevent duplicate tags in Set
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Tag)) return false;

        Tag other = (Tag) obj;

        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return name;
    }
}