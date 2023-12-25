package com.ltp.contacts.service;

import com.ltp.contacts.pojo.Contact;

public interface ContactService {
  Contact getContactById(String id);
  void saveContact(Contact contact);
  void updateContact(String id, Contact contact);
  void deleteContact(String id);
}

// Rest API
  // a resource is a nameable piece of data like employee name or contact
  // an api mediates the relationship between a consumer and a system
  // a rest api is an api that conforms to a set of guidelines
  // REST Guidelines
  // Resource: piece of data that you can name
  // URI: identifies location of a resource
  // Defines operations that can manipulate resources: GET, POST, PUT, DELETE
  // Resources is mostly described using JSON
  // JSON represents data in the form of key value pairs
  // Collection: a grouping of resources

// Here, we define a REST controller that acts as
// a mediator between the resources and the consumer
// it's recommended to create a service interface
// that'll be used to define every method that a
// service implementation must override. this helps to achieve decoupling
// the implementation can be injected into the
// controller as a contact service because it
// implements the contact service interface.
// the benefit of decoupling is shown when you replace your current implementation with a
// better one, you don't need to make any upstream changes in the controller as a bean
// of the new implementation will replace the old one and since both the new and old
// implement the service interface, the controller will continue to be able to use the
// new implementation
// Another benefit of decoupling the implementation from the service is that it
// it allows you to create more than one implementation and use conditionalonproperty
// annotation to determine what implementation bean will be put in the springboot container
// depending on the a specified property value
// for example, you can have one implementation bean be put in the container if running on
// port 8080 and another on 9090. both implement
// the service interface so there'll be no conflicts with the controller upstream
