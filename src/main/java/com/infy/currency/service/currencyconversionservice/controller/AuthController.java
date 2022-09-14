package com.infy.currency.service.currencyconversionservice.controller;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.infy.currency.service.currencyconversionservice.dto.SignUpRequestDTO;
import com.infy.currency.service.currencyconversionservice.repo.ICustomerRepo;
import com.infy.currency.service.currencyconversionservice.repo.RoleRepository;
import com.infy.currency.service.currencyconversionservice.service.ICustomerService;

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
    
    @Autowired
    private ICustomerService customerService;

    @PostMapping(path = "/signin", produces = "application/json")
    public ResponseEntity<User> authenticateUser(@RequestBody LoginRequestDTO loginDto) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        Optional<User> optUser = customerRepository.findByUsername(loginDto.getUsernameOrEmail());
        User signInUser = optUser.orElseThrow(()->new Exception("Could not find User!"));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<User>(signInUser, HttpStatus.ACCEPTED);
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
        user.setOtpToken(customerService.generateToken());

        Role roles = roleRepository.findByName(signUpDto.getRole()).get();
        user.setRoles(Collections.singleton(roles));

        customerRepository.save(user);

        return signUpDto;
    }
}
