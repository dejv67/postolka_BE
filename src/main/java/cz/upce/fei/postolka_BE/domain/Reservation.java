package cz.upce.fei.postolka_BE.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import cz.upce.fei.postolka_BE.dto.ReservationResponseDtoV1;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
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
    @Column(columnDefinition = "state")
    @Type( type = "pgsql_enum" )
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


    public Reservation(long id, LocalDateTime fromDate, LocalDateTime toDate, String note, State state,
                       LocalDateTime createDate, LocalDateTime modifDate, String modifUser, User author) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.note = note;
        this.state = state;
        this.createDate = createDate;
        this.modifDate = modifDate;
        this.modifUser = modifUser;
        this.author = author;
    }

    public Reservation(LocalDateTime fromDate, LocalDateTime toDate, String note, State state, LocalDateTime createDate,
                       LocalDateTime modifDate, String modifUser, User author) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.note = note;
        this.state = state;
        this.createDate = createDate;
        this.modifDate = modifDate;
        this.modifUser = modifUser;
        this.author = author;
    }

    public ReservationResponseDtoV1 toDto(){
        return new ReservationResponseDtoV1(
                getId(),
                getFromDate(),
                getToDate(),
                getNote(),
                getState(),
                getCreateDate(),
                getModifDate(),
                getModifUser(),
                getAuthor()
        );
    }
}
