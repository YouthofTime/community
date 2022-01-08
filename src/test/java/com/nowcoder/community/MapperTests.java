package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user=userMapper.selectById(1);
        System.out.println(user);
        user=userMapper.selectByName("SYSTEM");
        System.out.println(user);
        user=userMapper.selectByEmail("nowcoder1@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user=new User();
        user.setActivationCode("1");
        user.setCreateTime(new Date());
        user.setEmail("269@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setPassword("123");
        user.setSalt("abc");
        user.setStatus(1);
        user.setType(1);
        user.setUsername("张三");
        int row=userMapper.insertUser(user);
        System.out.println(row);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUser(){
        userMapper.updateHeaderUrl(1,"http://www.nowcoder.com/100.png");
        userMapper.updatePassword(1,"123456");
        userMapper.updateStatus(1,0);

    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> posts=discussPostMapper.selectPosts(0,0,10);
        for(DiscussPost post:posts)
            System.out.println(post);
        int rows=discussPostMapper.selectRows(0);
        System.out.println(rows);
    }

}
