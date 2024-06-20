package com.lucasmoraist.devideosapi.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Entity(name = "t_users")
@Table(name = "t_users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 180)
    private String name;
    @Email @Column(nullable = false, unique = true, length = 180)
    private String email;
    @Column(nullable = false)
    private String password;

}
