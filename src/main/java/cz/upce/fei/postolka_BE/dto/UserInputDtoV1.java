package cz.upce.fei.postolka_BE.dto;

import cz.upce.fei.postolka_BE.domain.Role;
import cz.upce.fei.postolka_BE.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDtoV1 {

    @NotNull
    @NotBlank
    @Size(max = 50, min = 1)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 50, min = 1)
    private String surname;

    @NotNull
    @NotBlank
    @Size(max = 100, min = 6)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 100, min = 3)
    private String password;

    @NotNull
    @NotBlank
    private String role;

    @NotNull
    private LocalDateTime modifDate;

    public User toEntity() {
        return new User(
                getName(),
                getSurname(),
                getEmail(),
                getPassword(),
                Role.valueOf(getRole()),
                getModifDate()
        );

    }

    public User toEntity(final long id) {
        return new User(
                id,
                getName(),
                getSurname(),
                getEmail(),
                getPassword(),
                Role.valueOf(getRole()),
                getModifDate()
        );
    }
}
