package cz.upce.fei.postolka_BE.dto;

import cz.upce.fei.postolka_BE.domain.Reservation;
import cz.upce.fei.postolka_BE.domain.State;
import cz.upce.fei.postolka_BE.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInputDtoV1 {

    @NotNull
    private LocalDateTime fromDate;

    @NotNull
    private LocalDateTime toDate;

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
    private String modifUser;

    @NotNull
    @NotBlank
    private User author;

    public Reservation toEntity(){
        return new Reservation(
                getFromDate(),
                getToDate(),
                getNote(),
                State.valueOf(getState()),
                getCreateDate(),
                getModifDate(),
                getModifUser(),
                getAuthor()
        );
    }

    public Reservation toEntity(final long id){
        return new Reservation(
                id,
                getFromDate(),
                getToDate(),
                getNote(),
                State.valueOf(getState()),
                getCreateDate(),
                getModifDate(),
                getModifUser(),
                getAuthor()
        );
    }
}
