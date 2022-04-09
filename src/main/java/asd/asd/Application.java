package asd.asd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        System.out.println("\n\n\nMain started....");
        SpringApplication.run(Application.class, args);
        
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("demo");
        user.setPassword(passwordEncoder.encode("demo"));
        user.setEnabled(true);
        user.setRoles(List.of("ROLE_USER")); 

        User admin = new User();
        admin.setName("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEnabled(true);
        admin.setRoles(List.of("ROLE_ADMIN"));

        userRepository.saveAll(List.of(user, admin));

        Car car = new Car();
        car.setModel("Fiat");

        carRepository.saveAll(List.of(car));
    }
}
