package com.infy.currency.service.currencyconversionservice.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.currency.service.currencyconversionservice.bean.Role;
import com.infy.currency.service.currencyconversionservice.bean.User;
import com.infy.currency.service.currencyconversionservice.dto.LoginRequestDTO;
import com.infy.currency.service.currencyconversionservice.dto.ResponseDTO;
import com.infy.currency.service.currencyconversionservice.dto.SignUpRequestDTO;
import com.infy.currency.service.currencyconversionservice.repo.ICustomerRepo;
import com.infy.currency.service.currencyconversionservice.repo.RoleRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ICustomerRepo customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/signin", produces = "application/json")
    public ResponseDTO authenticateUser(@RequestBody LoginRequestDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        ResponseDTO responseDTO = new ResponseDTO();        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        responseDTO.setMessage("User Added succesfully");
        return responseDTO;
    }

    @PostMapping(path = "/signup", produces = "application/json")
    public SignUpRequestDTO registerUser(@RequestBody SignUpRequestDTO signUpDto){

        // add check for username exists in a DB
        if(customerRepository.existsByUsername(signUpDto.getName())){
        	SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        	signUpRequestDTO.setMessage("User Already Taken");
            return  signUpRequestDTO;
        }

        // add check for email exists in DB
        if(customerRepository.existsByEmail(signUpDto.getEmail())){
        	SignUpRequestDTO user2 = new SignUpRequestDTO();
        	user2.setMessage("Email Already Taken");
            return user2;
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setAge(signUpDto.getAge());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        customerRepository.save(user);

        return signUpDto;

    }
}
