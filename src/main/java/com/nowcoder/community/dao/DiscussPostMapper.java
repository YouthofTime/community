package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectPosts(int userId,int offset,int limit);

    // 只有一个参数且用于动态sql中的if语句中必须加注解@Param
    int selectRows(@Param("userId") int userId);
}
