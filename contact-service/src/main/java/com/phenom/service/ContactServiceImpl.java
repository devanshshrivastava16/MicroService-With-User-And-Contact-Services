package com.phenom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phenom.entity.Contact;
import com.phenom.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> getContactsOfUsers(Long userId) {
        return contactRepository.findByUserId(userId);
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        Contact old = contactRepository.findById(id).orElse(null);

        if (old != null) {
            old.setContactName(contact.getContactName());
            old.setEmail(contact.getEmail());
            old.setUserId(contact.getUserId());
            return contactRepository.save(old);
        }

        return null;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}