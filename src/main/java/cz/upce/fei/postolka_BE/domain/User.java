package cz.upce.fei.postolka_BE.domain;

import cz.upce.fei.postolka_BE.PgSQLEnumType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
//@TypeDef(name = "role", typeClass = PgSQLEnumType.class)
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

    @Column
    private LocalDateTime modifDate;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Reservation> reservations = Collections.emptyList();

    public User(Long id, String name, String surname, String email, String password, LocalDateTime modifDate, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.modifDate = modifDate;
        this.role = role;
    }

    public User(String name, String surname, String email, String password, LocalDateTime modifDate, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.modifDate = modifDate;
        this.role = role;
    }
}