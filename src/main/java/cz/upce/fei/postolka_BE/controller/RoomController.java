package cz.upce.fei.postolka_BE.controller;

import cz.upce.fei.postolka_BE.domain.Reservation;
import cz.upce.fei.postolka_BE.domain.Room;
import cz.upce.fei.postolka_BE.dto.RoomResponseDtoV1;
import cz.upce.fei.postolka_BE.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.service.ReservationService;
import cz.upce.fei.postolka_BE.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final ReservationService reservationService;
    @GetMapping("")
    public ResponseEntity<List<RoomResponseDtoV1>> findAll() throws ResourceNotFoundException {
        var result = roomService.findAll();

        return ResponseEntity.ok(result
                .stream()
                .map(Room::toDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = roomService.findById(id);

        return ResponseEntity.ok(result.toDto());
    }

    @GetMapping(path = "/roomReservations", params = {"roomId", "fromDate", "toDate"})
    public ResponseEntity<?> findByDatesAndRoomId(@RequestParam("roomId") String roomId, @RequestParam("fromDate") String fromDate,
                                               @RequestParam("toDate") String toDate){

        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDate fromDateConverted = LocalDate.parse(fromDate,formatter);
        LocalDate toDateConverted = LocalDate.parse(toDate,formatter);
        Long lRoomId = Long.parseLong(roomId);

        var result = reservationService.findByDatesAndRoomId(fromDateConverted, toDateConverted, lRoomId);

        if(!result.isEmpty()){
            return ResponseEntity.ok(result
                    .stream()
                    .map(Reservation::toDto)
                    .collect(Collectors.toList())
            );
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
