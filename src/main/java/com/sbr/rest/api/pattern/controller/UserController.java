package com.sbr.rest.api.pattern.controller;

import com.sbr.rest.api.pattern.events.Event;
import com.sbr.rest.api.pattern.model.User;
import com.sbr.rest.api.pattern.model.request.UserRequest;
import com.sbr.rest.api.pattern.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user-service", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest.getUser().getUserId(), userRequest.getUser().getFirstName(), userRequest.getUser().getLastName());
        return ResponseEntity.ok("Success");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateAddress(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest.getContacts(), userRequest.getAddresses()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Event>> createUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getEvents(id));
    }
}
