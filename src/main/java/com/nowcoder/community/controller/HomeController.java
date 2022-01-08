package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public String getIndex(Model model, Page page){
        page.setRows(discussPostService.findRows(0));
        page.setPath("/index");
        System.out.println(page.getFrom()+"->"+page.getTo()+" current:"+page.getCurrent());
        List<DiscussPost> list=discussPostService.findPosts(0,page.getOffset(),page.getLimit());
        List<Map<String,Object>> posts=new ArrayList<>();
        for(DiscussPost post:list){
            Map<String,Object> map=new HashMap<>();
            User user=userService.findUserById(post.getUserId()); // 通过帖子外键userId查询出用户
            // 整合discussPost和User对象
            map.put("post",post);
            map.put("user",user);
            posts.add(map);
        }
        model.addAttribute("posts",posts);
        return "/index";
    }
}
