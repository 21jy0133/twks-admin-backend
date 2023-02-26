package com.kh.twks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.twks.rest.employee.EmployeeRepository;
import com.kh.twks.rest.employee.Employee;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    
    @Autowired
    EmployeeRepository userRepository;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String, Object> getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> userMap = new HashMap<>();
        Employee employee = userRepository.findEmployeeByEmail(authentication.getName());
        //userMap.put("detail", userRepository.findEmployeeByEmail(authentication.getName()));
        userMap.put("username", authentication.getName());
        userMap.put("department", employee.getDepartment());
        userMap.put("rank", employee.getRank());
        userMap.put("jobTitle", employee.getJobTitle());
        userMap.put("name", employee.getName());
        userMap.put("error", false);
        
        return userMap;
    }
}
