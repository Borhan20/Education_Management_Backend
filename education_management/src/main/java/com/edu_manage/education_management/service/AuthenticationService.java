package com.edu_manage.education_management.service;

import com.edu_manage.education_management.auth.AuthenticationRequest;
import com.edu_manage.education_management.auth.AuthenticationResponse;
import com.edu_manage.education_management.auth.RegisterRequest;
import com.edu_manage.education_management.entity.Role;
import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.repository.EMSUserRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class AuthenticationService {
    private final EMSUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;




    //generate token when login
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        //get user details using user mail
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        //generate token using the details
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    //generate token when register
    public AuthenticationResponse register(RegisterRequest request) {

        //make a
        // user with necessary details
        var user = EMSUser.builder()
                .phone(request.getPhone())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        //if user email is not taken by anyone then generate token
        if(!repository.existsByEmail(request.getEmail())){
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        //user email already exits
        else{
            return AuthenticationResponse.builder()
                    .Error("User already exits")
                    .build();
        }

    }

    //find user details using mail
    public EMSUser findByEmail(String email) {
        Optional<EMSUser> user = repository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        throw new RuntimeException("User not found in " + email);
    }

    //update users verification status
    public EMSUser updateVerificationStatus(EMSUser user){
        return repository.save(user);
    }
}
