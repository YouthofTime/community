package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int updateStatus(int id,int status);

    int insertUser(User user);

    int updateHeaderUrl(int id,String headerUrl);

    int updatePassword(int id,String password);

}
