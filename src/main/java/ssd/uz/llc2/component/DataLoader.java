package ssd.uz.llc2.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ssd.uz.llc2.db.domain.Role;
import ssd.uz.llc2.db.domain.User;
import ssd.uz.llc2.db.repository.RoleRepository;
import ssd.uz.llc2.db.repository.admin.UserRepository;

import java.sql.Date;
import java.util.UUID;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(1l, "ROLE_ADMIN");
        Role roleModerator = new Role(2l, "ROLE_TEACHER");
        Role roleUser = new Role(3l, "ROLE_STUDENT");
        try {
            roleRepository.save(roleAdmin);
            roleRepository.save(roleModerator);
            roleRepository.save(roleUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        User admin = new User();
        admin.setId(UUID.fromString("b4bdfc03-9b16-4fc6-89c0-a0aee275f0c8"));
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPhone("999999996");
        admin.setAddress(" viloyati");
        admin.setBirthDate(Date.valueOf("2000-11-13"));
        admin.setPassword(passwordEncoder.encode("12345"));
        admin.setUsername("admin");
        admin.setRole(roleRepository.findByName("ROLE_ADMIN").get());

        User teacher = new User();
        teacher.setId(UUID.fromString("6a1131a4-fc43-4a48-9e20-bf5be84b6ca9"));
        teacher.setFirstName("teacher");
        teacher.setLastName("teacher");
        teacher.setEmail("teacher@gmail.com");
        teacher.setPhone("999999997");
        teacher.setAddress(" viloyati");
        teacher.setBirthDate(Date.valueOf("2000-11-14"));
        teacher.setPassword(passwordEncoder.encode("12345"));
        teacher.setUsername("teacher");
        teacher.setRole(roleRepository.findByName("ROLE_TEACHER").get());

        User student = new User();
        student.setId(UUID.fromString("7811fef1-5472-41e1-86e5-6814a511dfc4"));
        student.setFirstName("student");
        student.setLastName("student");
        student.setEmail("student@gmail.com");
        student.setPhone("999999998");
        student.setAddress(" viloyati");
        student.setBirthDate(Date.valueOf("2000-11-12"));
        student.setPassword(passwordEncoder.encode("12345"));
        student.setUsername("student");
        student.setRole(roleRepository.findByName("ROLE_STUDENT").get());
        try {
//            userRepository.save(admin);
//            userRepository.save(teacher);
//            userRepository.save(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
