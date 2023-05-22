package cz.upce.fei.postolka_BE.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.fei.postolka_BE.PgSQLEnumType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//import org.hibernate.annotations.TypeDef;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
//@TypeDef(name = "state", typeClass = PgSQLEnumType.class)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDateTime fromDate;

    @Column
    private LocalDateTime toDate;

    @Column
    private String note;

    @Enumerated(EnumType.STRING)
    @Column
    private State state;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime modifDate;

    @Column
    private String modifUser;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @ToString.Exclude
    @JsonIgnore
    private User author;

    @ManyToMany(mappedBy = "reservations")
    private List<Room> rooms = Collections.emptyList();
}
