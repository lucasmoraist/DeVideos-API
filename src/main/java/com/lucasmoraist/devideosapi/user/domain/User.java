package com.lucasmoraist.devideosapi.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "t_users")
@Table(name = "t_users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email @Column(nullable = false, unique = true, length = 180)
    private String email;
    @Column(nullable = false, length = 20)
    @Min(6)
    private String password;

}
