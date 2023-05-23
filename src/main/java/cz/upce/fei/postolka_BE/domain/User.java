package cz.upce.fei.postolka_BE.domain;


import cz.upce.fei.postolka_BE.dto.UserResponseDtoV1;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "postolka_reservation_system")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "role")
    @Type( type = "pgsql_enum" )
    private Role role;
    @Column
    private LocalDateTime modifDate;

    @OneToMany(mappedBy = "author")
    private List<Reservation> reservations = Collections.emptyList();

    public User(Long id, String name, String surname, String email, String password, Role role, LocalDateTime modifDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.modifDate = modifDate;
    }

    public User(String name, String surname, String email, String password, Role role, LocalDateTime modifDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.modifDate = modifDate;
    }

    public UserResponseDtoV1 toDto() {
        return new UserResponseDtoV1(
                getId(),
                getName(),
                getSurname(),
                getEmail(),
                getPassword(),
                getRole(),
                getModifDate()
        );
    }
}