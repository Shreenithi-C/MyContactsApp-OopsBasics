package com.tagManagement;

import java.util.HashSet;
import java.util.Set;

public class TagService {

    private Set<Tag> tags = new HashSet<>();

    public void createTag(String name) {

        Tag tag = new Tag(name);

        if (tags.add(tag)) {
            System.out.println("Tag created successfully.");
        } else {
            System.out.println("Tag already exists.");
        }
    }

    public Set<Tag> getAllTags() {
        return tags;
    }
    
    public Tag findTag(String name) {

        for (Tag tag : tags) {

            if (tag.getName().equalsIgnoreCase(name)) {
                return tag;
            }
        }

        return null;
    }
}