package com.edu_manage.education_management.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emsuser")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EMSUser implements UserDetails{
    @Id
    @Column(name = "user_id")
    private UUID userId;

    private String email;
    private String phone;
    private String name;
    private String password;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



    // private Set<Role> role;


    // Getters, setters, constructors



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }
    @Override
    public String getUsername() {

        return email;
        // TODO Auto-generated method stub

    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


    public void setAdminRoles(Role adminRole) {
       this.role = adminRole;
       System.out.println(adminRole);
   }
}