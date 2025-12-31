package com.phenom.controller;

import com.phenom.entity.Contact;
import com.phenom.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // Corrected imports

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // CREATE
    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }

    // READ ALL
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // READ BY CONTACT ID
    @GetMapping("/{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    // USED BY USER-SERVICE
    @GetMapping("/user/{userId}")
    public List<Contact> getContactsByUserId(@PathVariable Long userId) {
        return contactService.getContactsOfUsers(userId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "Contact deleted with id : " + id;
    }
}