package com.phenom.service;

import com.phenom.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private List<User> list=List.of(
            new User("8765432184","Arpit",1234L),
            new User("8539734663","Ankit",5678L),
            new User("9273511562","Arvind",2468L)
    );

    @Override
    public User getUser(Long id) {
        return list.stream().filter(user->user.getUserId().equals(id)).findAny().orElse(null);
    }

}
