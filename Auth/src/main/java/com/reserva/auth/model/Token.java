package com.reserva.auth.model;
import com.reserva.auth.model.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  @AllArgsConstructor
@Builder
@Entity
@Table(name="tokens")
public class Token{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;
    private Boolean revoked;
    private Boolean expired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

