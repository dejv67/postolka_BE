package cz.upce.fei.postolka_BE.dto;

import cz.upce.fei.postolka_BE.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserResponseDtoV1 {

    private Long Id;

    private String name;

    private String surname;

    private String email;

    private Role role;

    private LocalDateTime modifDate;

    public UserResponseDtoV1(Long id, String name, String surname, String email, Role role, LocalDateTime modifDate) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.Id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.modifDate = modifDate;
     }
}
