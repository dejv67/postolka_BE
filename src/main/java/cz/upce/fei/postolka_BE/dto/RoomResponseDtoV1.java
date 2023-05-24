package cz.upce.fei.postolka_BE.dto;

import cz.upce.fei.postolka_BE.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomResponseDtoV1 {

    private long id;

    private String name;

    private String description;

    private int numOfBeds;

    public RoomResponseDtoV1(long id, String name, String description, int numOfBeds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numOfBeds = numOfBeds;
    }
}
