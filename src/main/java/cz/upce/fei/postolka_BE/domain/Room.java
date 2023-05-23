package cz.upce.fei.postolka_BE.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Room {

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "num_of_beds")
    private int numOfBeds;

    @ManyToMany
    @JoinTable(
            name = "reserved_rooms",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    @ToString.Exclude // It will prevent to infinity loop in Lombok ToString generation because field from each class points to themselves
    @JsonIgnore
    private List<Reservation> reservations = Collections.emptyList();


}
