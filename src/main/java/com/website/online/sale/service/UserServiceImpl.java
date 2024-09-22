package com.website.online.sale.service;

import com.website.online.sale.dtos.response.CheckLoginResponse;
import com.website.online.sale.model.User;
import org.springframework.stereotype.Service;
import com.website.online.sale.repository.UserRepository;

import java.util.Objects;

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

    @Override
    public CheckLoginResponse checkLogin(String username, String pass) {
        CheckLoginResponse res = new CheckLoginResponse();
        res.setUserName(username);
        res.setPassWord(pass);
        User user = userRepository.findFirstByUserNameAndPass(username, pass);
        if (Objects.isNull(user)){
            res.setStatus(false);
            res.setError("Sai mật khẩu hoặc tài khoản");
        }else res.setStatus(true);
        return res;
    }
}
