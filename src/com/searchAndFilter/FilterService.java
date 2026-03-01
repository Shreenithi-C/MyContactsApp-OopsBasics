package com.searchAndFilter;

import java.util.List;
import com.contactManagement.Contact;

public interface FilterService {

    List<Contact> filterByTag(String tag);

    List<Contact> filterByDateAdded();

    List<Contact> filterByFrequentlyContacted();
}