package cz.upce.fei.postolka_BE.dto;

import cz.upce.fei.postolka_BE.domain.Reservation;
import cz.upce.fei.postolka_BE.domain.Room;
import cz.upce.fei.postolka_BE.domain.State;
import cz.upce.fei.postolka_BE.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInputDtoV1 {

    @NotNull
    private LocalDate fromDate;

    @NotNull
    private LocalDate toDate;

    @Size(max = 500)
    private String note;

    @NotNull
    @NotBlank
    private String state;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private LocalDateTime modifDate;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String modifUser;

    @NotNull
    private int author_id;

    @NotNull
    List<Long> roomIds;

    public Reservation toEntity(){
        Reservation reservation = new Reservation(
                getFromDate(),
                getToDate(),
                getNote(),
                State.valueOf(getState()),
                getCreateDate(),
                getModifDate(),
                getModifUser()
        );

        return linkForeignKeys(reservation);
    }

    private Reservation linkForeignKeys(Reservation reservation){
        User user = new User();
        user.setId(getAuthor_id());

        reservation.setAuthor(user);

        List<Room> rooms = new ArrayList<>();
        for(Long roomID : roomIds){
            Room room = new Room();
            room.setId(roomID);
            rooms.add(room);
        }
        reservation.setRooms(rooms);

        return reservation;
    }
}
