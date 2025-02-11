package eu.mithril.invoiceservice.service;

import java.util.UUID;

import eu.mithril.invoiceservice.model.User;

public class UserService {

    public User findById(String id) {
        return new User(id, UUID.randomUUID().toString());
    }
}
