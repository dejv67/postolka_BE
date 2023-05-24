package cz.upce.fei.postolka_BE.dto;

import cz.upce.fei.postolka_BE.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponseDtoV1 {

    private Long Id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Role role;

    private LocalDateTime modifDate;

    public UserResponseDtoV1(Long id, String name, String surname, String email, String password, Role role, LocalDateTime modifDate) {
        this.Id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.modifDate = modifDate;
     }
}
