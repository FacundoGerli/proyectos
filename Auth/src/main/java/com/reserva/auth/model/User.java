package com.reserva.auth.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//La idea seria que la clase cliente este en un servicio aparte
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Token> tokens;
}
