// package com.edu_manage.education_management.admin;




// import java.util.Collections;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.edu_manage.education_management.entity.EMSUser;
// import com.edu_manage.education_management.entity.Role;
// import com.edu_manage.education_management.repository.EMSUserRepository;
// import com.edu_manage.education_management.repository.RoleRepository;

// @Component
// public class DataLoader implements CommandLineRunner {
//     @Autowired
//     private EMSUserRepository userRepository;

//     @Autowired
//     private RoleRepository roleRepository;

//     // @Autowired
//     // private BCryptPasswordEncoder passwordEncoder;

//     @Override
//     public void run(String... args) throws Exception {
//         initializeDefaultAdmin();
//     }

//     private void initializeDefaultAdmin() {
//         Role adminRole = roleRepository.findByRole("ADMIN").orElseGet(() -> {
//             Role role = new Role();
//             role.setRole("ADMIN");
//             role.setDescription("Admin Role");
//             return roleRepository.save(role);
//         });

//         EMSUser adminUser = userRepository.findByEmail("admin@example.com").orElseGet(() -> {
//             EMSUser user = new EMSUser();
//             user.setEmail("admin@example.com");
//             // Set other user details
//             user.setPassword(passwordEncoder("adminpassword"));
//             user.setStatus(true);
//             user.setRoles(Collections.singleton(adminRole));
//             return userRepository.save(user);
            
//         });
//     }

//     private String passwordEncoder(String string) {
//         // TODO Auto-generated method stub
//         return new BCryptPasswordEncoder().toString();
//     }
    

    
// }

