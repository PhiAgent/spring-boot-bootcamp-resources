package com.ltp.contacts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.service.ContactService;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id){
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    // rest Get operation:
    // @PathVariable is used to fetch a defined item in the path of the request
    // @ResponseBody is used to serialize the response of the request. without it,
    // springboot will send back a plain java object
    // @ResponseBody can be applied at the class level or at the handler method
    // level
    // you can use the terminal to act as a consumer of the resource and use curl to
    // send request to
    // the server port: curl localhost:8080/contacts
    // @RestController combines a @ResponseBody and @Controller so you can use it
    // instead of both
    // @PathVariable is more suitable for REST applications than @RequestParams
}
