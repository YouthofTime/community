package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello SpringBoot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    @ResponseBody
    public void http(HttpServletRequest request, HttpServletResponse response){
        // 获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames= request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name=headerNames.nextElement(); // 请求头名字
            String value=request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try(PrintWriter pw=response.getWriter();) {
            pw.write("<p1>牛客网</p1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get方式1 参数匹配
    @RequestMapping(path="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@RequestParam(name = "name",required = false,defaultValue ="张三")String name,
                             @RequestParam(name="age",required = false,defaultValue = "20")int age){
        System.out.println(name+" "+age);
        return "some students";
    }

    // Get方式2 路径匹配
    @RequestMapping(path="/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudentPath(@PathVariable("id")int id){
        System.out.println(id);
        return "a student";
    }

    // Post请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String getStudents(String name,int age){
        System.out.println(name+" "+age);
        return "a student";
    }

    // 响应(返回ModelAndView对象）
    @RequestMapping("/teacher")
    public ModelAndView getTeacher(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);
        mav.setViewName("/demo/view"); // 默认添加html后缀
        return mav;
    }

    // 响应（Model和View捆绑）
    @RequestMapping("/school")
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age",100);
        return "/demo/view";
    }

    // 响应（异步请求)
    // 返回JSON格式数据  Java对象->JSON格式数据->JS对象
    @RequestMapping("/emp")
    @ResponseBody
    public Map<String,Object> getEmployee(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",20);
        return map;
    }

    @RequestMapping("/emps")
    @ResponseBody
    public List<Map<String,Object>> getEmployees(){
        List<Map<String,Object>> emps=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",20);
        emps.add(map);
        map=new HashMap<>();
        map.put("name","李四");
        map.put("age",23);
        emps.add(map);
        map=new HashMap<>();
        map.put("name","王五");
        map.put("age",24);
        emps.add(map);
        return emps;
    }
}
