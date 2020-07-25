package com.example.customer.controller;

import com.example.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("customer/users")
public class CustomerUserController {

    private static final String REST_URL_PREFIX = "http://localhost:3001/certification/users";
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public String add(User user) {
        try {
            restTemplate.postForObject(REST_URL_PREFIX, user, String.class);
        } catch (Exception e) {
            return String.format("添加失败[%s]", e.getMessage());
        }
        return String.format("添加成功[%s]", user.getId());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            restTemplate.delete(String.format(REST_URL_PREFIX + "/%s", id), String.class);
        } catch (Exception e) {
            return String.format("删除失败[%s]", e.getMessage());
        }
        return "删除成功";
    }

    @PutMapping
    public String update(User user) {
        try {
            restTemplate.put(REST_URL_PREFIX, user, String.class);
        } catch (Exception e) {
            return String.format("修改失败[%s]", e.getMessage());
        }
        return "修改成功";
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        try {
            return restTemplate.getForObject(String.format(REST_URL_PREFIX + "/%s", id), User.class);
        } catch (Exception e) {
            return new User(String.format("修改失败[%s]", e.getMessage()), "");
        }
    }

}
