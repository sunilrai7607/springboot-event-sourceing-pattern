package com.sbr.rest.api.pattern.model.request;

import com.sbr.rest.api.pattern.model.Address;
import com.sbr.rest.api.pattern.model.Contact;
import com.sbr.rest.api.pattern.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private User user;

    private Set<Address> addresses;

    private Set<Contact> contacts;

}
