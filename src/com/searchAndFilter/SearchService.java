package com.searchAndFilter;

import java.util.List;

import com.contactManagement.Contact;

public interface SearchService {

    List<Contact> searchByName(String name);

    List<Contact> searchByPhone(String phone);

    List<Contact> searchByEmail(String email);

    List<Contact> searchByTag(String tag);
}