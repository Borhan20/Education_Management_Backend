package com.edu_manage.education_management.controller;

import com.edu_manage.education_management.auth.AuthenticationRequest;
import com.edu_manage.education_management.auth.AuthenticationResponse;
import com.edu_manage.education_management.auth.RegisterRequest;
import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(service.authenticate(request));
    }

    //find user details using email
    @GetMapping("/getUser/{email}")
    public EMSUser getEMSUser(@PathVariable String email){
        return service.findByEmail(email);
    }

    //update verification status of verified user

    //demo controller
    @GetMapping("/demo")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from demo");
    }
}
