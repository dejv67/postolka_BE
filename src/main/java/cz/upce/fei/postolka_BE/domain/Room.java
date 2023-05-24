package cz.upce.fei.postolka_BE.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import cz.upce.fei.postolka_BE.dto.RoomResponseDtoV1;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "room", schema = "postolka_reservation_system")
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

    @ManyToMany(mappedBy = "rooms")
    private List<Reservation> reservations = Collections.emptyList();


    public RoomResponseDtoV1 toDto(){
        return new RoomResponseDtoV1(
                getId(),
                getName(),
                getDescription(),
                getNumOfBeds()
        );
    }

}
