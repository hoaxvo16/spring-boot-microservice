package com.example.springrestapi.controllers.protectedControllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springrestapi.configurations.RequestConfig;
import com.example.springrestapi.models.UserDto;
import com.example.springrestapi.services.interfaces.UserService;

@RestController
@RequestMapping(RequestConfig.BASE_PROTECTED_URL + "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@Valid @RequestBody UserDto body, @PathVariable(value = "id") UUID id)
            throws Exception {
        UserDto user = userService.editUser(body, id);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }
}
