package com.example.certification.controller;

import com.example.certification.dao.UserRepository;
import com.example.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("certification/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String add(User user){
        userRepository.save(user);
        return "添加成功" + user.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return "删除成功";
    }

    @PutMapping
    public String update(User user) {
        userRepository.save(user);
        return "修改成功";
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        return userRepository.getOne(id);
    }

    @GetMapping("/list")
    public List<User> list(){
        return userRepository.findAll();
    }
}
