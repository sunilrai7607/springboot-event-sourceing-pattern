package com.sbr.rest.api.pattern.service;

import com.sbr.rest.api.pattern.events.Event;
import com.sbr.rest.api.pattern.events.UserAddAddressEvent;
import com.sbr.rest.api.pattern.events.UserAddContactEvent;
import com.sbr.rest.api.pattern.events.UserCreateEvent;
import com.sbr.rest.api.pattern.model.Address;
import com.sbr.rest.api.pattern.model.Contact;
import com.sbr.rest.api.pattern.model.User;
import com.sbr.rest.api.pattern.repository.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final EventStore eventStore;

    @Autowired
    public UserService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void createUser(String userId, String firstName, String lastName) {
        eventStore.addEvent(userId, new UserCreateEvent(new User(userId, firstName, lastName)));
    }

    public User updateUser(String userId, Set<Contact> contacts, Set<Address> addresses) {

        User user = Optional.ofNullable(UserUtility.recreateUser(userId, eventStore))
                .orElseThrow(() -> new RuntimeException("Invalid Resource Id " + userId));
        if (addresses != null) addresses.stream()
                .filter(address -> addresses.contains(address))
                .forEach(address -> eventStore.addEvent(userId, new UserAddAddressEvent(address)));


        if (contacts != null) contacts.stream()
                .filter(contact -> contacts.contains(contact))
                .forEach(contact -> eventStore.addEvent(userId, new UserAddContactEvent(contact)));

        return user;
    }

    public List<Event> getEvents(String id) {
        return eventStore.getEvents(id);
    }
}
