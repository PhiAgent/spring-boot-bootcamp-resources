package com.ltp.contacts.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.contacts.exception.NoContactException;
import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact getContactById(String id) throws NoContactException{
        return contactRepository.getContact(findIndexById(id));
    }

    @Override
    public List<Contact> getContacts(){
        return contactRepository.getContacts();
    }

    // "c0e720af-3910-4441-90b2-0ad98fd489db"

    @Override
    public void saveContact(Contact contact){
        contactRepository.saveContact(contact);
    }

    @Override
    public void updateContact(String id, Contact contact) throws NoContactException{
        contactRepository.updateContact(findIndexById(id), contact);
    }

    @Override
    public void deleteContact(String id) throws NoContactException{
        contactRepository.deleteContact(findIndexById(id));
    }

    private int findIndexById(String id) throws NoContactException {
        return IntStream.range(0, contactRepository.getContacts().size())
            .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NoContactException());
    }

}
