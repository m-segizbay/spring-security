package kz.segizbay.spring_security_example.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import kz.segizbay.spring_security_example.models.Application;
import kz.segizbay.spring_security_example.models.MyUser;
import kz.segizbay.spring_security_example.repository.UserRepoistory;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {
    private List<Application> applicationList;
    private UserRepoistory userRepoistory;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadAppInDb(){
        Faker faker = new Faker();
        applicationList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();
    }

    public List<Application> allApplicationList() {
        return applicationList;
    }

    public Application getApplicationById(int id) {
        return applicationList.stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepoistory.save(user);
    }
}
