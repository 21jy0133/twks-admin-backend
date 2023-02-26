package com.kh.twks.controller;

import com.kh.twks.rest.employee.Employee;
import com.kh.twks.rest.employee.EmployeeRepository;
import com.kh.twks.service.JwtUserDetailsService;
import com.kh.twks.util.JwtTokenUtil;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.nio.charset.Charset;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    protected final Log logger = LogFactory.getLog(getClass());

    final EmployeeRepository userRepository;
    final AuthenticationManager authenticationManager;
    final JwtUserDetailsService userDetailsService;
    final JwtTokenUtil jwtTokenUtil;


    @Autowired
	private JavaMailSender mailSender;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest {
        private String email;
        private String password;
    }


    public AuthenticationController(EmployeeRepository userRepository, AuthenticationManager authenticationManager,
            JwtUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest req ) {
        Map<String, Object> responseMap = new HashMap<>();
        try {

            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(req.email, req.password));
            if (auth.isAuthenticated()) {
                logger.info("Logged In");
                UserDetails userDetails = userDetailsService.loadUserByUsername(req.email);
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("message", "Logged In");
                responseMap.put("accessToken", token);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest {
        @NotBlank
        private String email;
        @NotBlank
        private String name;
        @NotBlank
        private String address;
        private String password;
        @NotBlank
        private String kana;
        @NotBlank
        private String tel;
        @NotBlank
        private String postCode;
        @NotNull
        private Date birthday;
        @NotBlank
        private String departmentDeptCode;
        private String rankCode;
        @NotBlank
        private String jobTitleCode;
        @NotBlank
        private String sex;
        @NotNull
        private Date initDate;
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> saveUser(@Valid @RequestBody RegisterRequest user ) {
        Map<String, Object> responseMap = new HashMap<>();

        ModelMapper modelMapper = new ModelMapper();

        byte[] array = new byte[12]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        user.setPassword(generatedString);


        user.setRankCode("01");


        Employee employee = modelMapper.map(user, Employee.class);

        employee.setPassword(new BCryptPasswordEncoder().encode(user.password));

        int year = user.getInitDate().getYear() % 100;  

        String maxId = userRepository.getMaxEmpIdByYear(String.format("%02d", year));

        String newId;

        if (Objects.isNull(maxId)) {
            newId = year + "kh" + String.format("%04d", 1);

        } else {
            newId = year + "kh" + String.format("%04d", Integer.parseInt(maxId.split("kh")[1]) + 1);
        }
        employee.setEmpId(newId);
        UserDetails userDetails = userDetailsService.createUserDetails(employee.getEmail(), employee.getPassword());
        String token = jwtTokenUtil.generateToken(userDetails);
        userRepository.save(employee);
        responseMap.put("error", false);
        responseMap.put("email", employee.getEmail());
        responseMap.put("message", "Account created successfully");
        responseMap.put("token", token);
        responseMap.put("empId", employee.getEmpId());

        SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("xiaoyuebuptgmail.com");
		message.setTo(employee.getEmail());
		message.setSubject("KH株式会社からのお知らせ");
        String mailText = String.format("%s様\n\nKH株式会社からのお知らせです。\n\n %s様のパスワードをお伝えいたします。\n\n %s \n\n です。\n以上です。", employee.getName(), user.getName(),  user.password);

		message.setText(mailText);

		mailSender.send(message);


        return ResponseEntity.ok(responseMap);
    }

}
