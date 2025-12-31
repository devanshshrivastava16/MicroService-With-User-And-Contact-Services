package com.phenom.service;

import com.phenom.entity.Contact;

import java.util.List;

public interface ContactService {

    Contact saveContact(Contact contact);

    List<Contact> getAllContacts();

    List<Contact> getContactsOfUsers(Long userId);

    Contact getContactById(Long id);

    Contact updateContact(Long id, Contact contact);

    void deleteContact(Long id);
}
