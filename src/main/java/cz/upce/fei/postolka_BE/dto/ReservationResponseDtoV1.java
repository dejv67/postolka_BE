package cz.upce.fei.postolka_BE.dto;

import cz.upce.fei.postolka_BE.domain.Room;
import cz.upce.fei.postolka_BE.domain.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ReservationResponseDtoV1 {

    private long id;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String note;

    private State state;

    private LocalDateTime createDate;

    private LocalDateTime modifDate;

    private String modifUser;

    private List<Room> rooms = Collections.emptyList();

    public ReservationResponseDtoV1(long id, LocalDate fromDate, LocalDate toDate, String note, State state,
                                    LocalDateTime createDate, LocalDateTime modifDate, String modifUser, List<Room> rooms ) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.note = note;
        this.state = state;
        this.createDate = createDate;
        this.modifDate = modifDate;
        this.modifUser = modifUser;
        this.rooms = rooms;
    }
}
