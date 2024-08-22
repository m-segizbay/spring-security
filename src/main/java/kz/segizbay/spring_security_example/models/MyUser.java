package kz.segizbay.spring_security_example.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;

    private String password;

    private String roles;
}
