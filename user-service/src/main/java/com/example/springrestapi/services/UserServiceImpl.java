package com.example.springrestapi.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.springrestapi.entities.User;
import com.example.springrestapi.models.UserDto;
import com.example.springrestapi.repositories.UserRepository;
import com.example.springrestapi.services.interfaces.PublisherService;
import com.example.springrestapi.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherService publisherService;

    @Override
    @Transactional
    public UserDto editUser(UserDto user, UUID id) throws Exception {
        Optional<User> existedUser = userRepository.findById(id);
        if (existedUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User is not existed");
        }
        existedUser.get().setEmail(user.getEmail());
        existedUser.get().setFirstName(user.getFirstName());
        existedUser.get().setLastName(user.getLastName());
        existedUser.get().setPhone(user.getPhone());
        userRepository.save(existedUser.get());
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        User existedUser = userRepository.findByEmail(email);
        if (existedUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with " + email + "  doest not exist");
        }
        return existedUser;
    }

    @Override
    public User getUserByPhoneNumber(String phone) throws Exception {
        User existedUser = userRepository.findByPhone(phone);
        if (existedUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with phone number " + phone + "  doest not exist");
        }
        return existedUser;
    }

    @Override
    public User getUserByAccountId(UUID accountId) throws Exception {
        Optional<User> user = userRepository.findById(accountId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with account id " + accountId + "  doest not exist");
        }

        return user.get();
    }

    @Override
    public void checkOrderAccountId(UUID accountId, UUID messageId) throws Exception {
        Optional<User> user = userRepository.findById(accountId);
        System.out.println("Checking user id of order.....");
        if (user.isEmpty()) {
            String routingKey = "error.order.userIdOfOrderNotFound";

            publisherService.sendMessage(accountId, routingKey, messageId);
        }

        String routingKey = "checkUserIdOfOrder.order.success";
        publisherService.sendMessage(accountId, routingKey, messageId);

    }

}
