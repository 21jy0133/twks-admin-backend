package com.kh.twks.service;

import com.kh.twks.rest.employee.EmployeeRepository;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class JwtUserDetailsService implements UserDetailsService {

    final EmployeeRepository userRepository;

    public JwtUserDetailsService(EmployeeRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DisabledException{
        com.kh.twks.rest.employee.Employee user = userRepository.findEmployeeByEmail(email);

        Boolean enabled = true;
        
        if(user == null){
            throw new UsernameNotFoundException(email);
        }     

        if (!user.getDepartment().getDeptCode().equals("k01") && !user.getJobTitle().getCode().equals("k")) {
            enabled = false;
        }
         
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        System.out.println("detail");
        return new User(user.getEmail(), user.getPassword(), enabled, true, true, true, authorityList);
    }

    public UserDetails createUserDetails(String username, String password) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new User(username, password, authorityList);
    }
}
