package com.sbr.rest.api.pattern.service;

import com.sbr.rest.api.pattern.events.UserAddAddressEvent;
import com.sbr.rest.api.pattern.events.UserAddContactEvent;
import com.sbr.rest.api.pattern.events.UserCreateEvent;
import com.sbr.rest.api.pattern.model.User;
import com.sbr.rest.api.pattern.repository.EventStore;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class UserUtility {

    /**
     * Method to recreate user from Event store
     *
     * @param userId
     * @param eventStore
     * @return
     */
    public final static User recreateUser(String userId, EventStore eventStore) {
        return eventStore.getEvents(userId)
                .stream()
                .map(event -> {
                    User user = null;
                    if (event instanceof UserCreateEvent) {
                        UserCreateEvent userCreationEvent = (UserCreateEvent) event;
                        user = userCreationEvent.getUser();
                        user.setUserId(UUID.randomUUID().toString());
                    } else if (event instanceof UserAddAddressEvent) {
                        user.getAddresses().add(((UserAddAddressEvent) event).getAddress());

                    } else if (event instanceof UserAddContactEvent) {
                        user.getContacts().add(((UserAddContactEvent) event).getContact());
                    }
                    log.info("ReCreated User : {} ", user);
                    return user;
                }).findFirst().orElseThrow(() -> new RuntimeException("Not a valid userId"));
    }
}
