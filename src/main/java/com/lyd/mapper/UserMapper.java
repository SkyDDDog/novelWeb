package com.lyd.mapper;

import com.lyd.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);

    public int addUser(User user);

    public List<User> queryAllUser();

}
