 package com.edu_manage.education_management.admin;




 import java.util.Collections;
 import java.util.UUID;


 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.CommandLineRunner;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Component;

 import com.edu_manage.education_management.entity.EMSUser;
 import com.edu_manage.education_management.entity.Role;
 import com.edu_manage.education_management.repository.EMSUserRepository;
 import com.edu_manage.education_management.repository.RoleRepository;
 import com.edu_manage.education_management.config.ApplicationConfig;

 @Component
 public class DataLoader implements CommandLineRunner {
     @Autowired
     private EMSUserRepository userRepository;

     @Autowired
     private RoleRepository roleRepository;

      @Autowired
      private PasswordEncoder passwordEncoder;

     @Override
     public void run(String... args) throws Exception {
         initializeDefaultAdmin();
     }

     private void initializeDefaultAdmin() {
         Role adminRole = roleRepository.findByRole("ADMIN").orElseGet(() -> {
             Role role = new Role();
             role.setRole("ADMIN");
             role.setDescription("Admin Role");
             return roleRepository.save(role);
         });


         EMSUser adminUser = userRepository.findByEmail("admin@example.com").orElseGet(() -> {
             EMSUser user = new EMSUser();
             user.setUserId(UUID.randomUUID());
             user.setEmail("admin@example.com");
             user.setName("admin");
             user.setPhone("0123444");
             // Set other user details
             user.setPassword(passwordEncoder.encode("adminpassword"));
             user.setStatus(true);
             user.setAdminRoles(adminRole);
             return userRepository.save(user);
            
         });
     }

//     private String passwordEncoder(String string) {
//         // TODO Auto-generated method stub
//         return new BCryptPasswordEncoder().toString();
//     }
//

    
 }

