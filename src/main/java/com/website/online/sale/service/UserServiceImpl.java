package com.website.online.sale.service;

import org.springframework.stereotype.Service;
import com.website.online.sale.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Object getAllUser() {
        return userRepository.findAll();
    }
}
