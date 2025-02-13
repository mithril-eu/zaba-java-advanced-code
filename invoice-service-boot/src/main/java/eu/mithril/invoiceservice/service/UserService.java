package eu.mithril.invoiceservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import eu.mithril.invoiceservice.model.User;

@Service
public class UserService {

    public User findById(String id) {
        return new User(id, UUID.randomUUID().toString());
    }

}
