package com.lyd.service;

import com.lyd.pojo.User;

import java.util.List;

public interface UserService {

    public User queryUserByName(String name);

    public int addUser(User user);

    public List<User> queryAllUser();

}
