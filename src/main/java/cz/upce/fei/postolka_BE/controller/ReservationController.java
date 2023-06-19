package cz.upce.fei.postolka_BE.controller;

import cz.upce.fei.postolka_BE.domain.Reservation;
import cz.upce.fei.postolka_BE.dto.ReservationInputDtoV1;
import cz.upce.fei.postolka_BE.dto.ReservationResponseDtoV1;
import cz.upce.fei.postolka_BE.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("")
    public ResponseEntity<List<ReservationResponseDtoV1>> findAll() throws ResourceNotFoundException {
        var result = reservationService.findAll();

        return ResponseEntity.ok(result
                .stream()
                .map(Reservation::toDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = reservationService.findById(id);

        return ResponseEntity.ok(result.toDto());
    }

//    @GetMapping(path = "/dates", params = {"fromDate", "toDate"})
//    public ResponseEntity<?> findByDateBetween(@RequestParam("fromDate") String fromDate,
//                                         @RequestParam("toDate") String toDate) throws ResourceNotFoundException {
//
//        String pattern = "yyyy-MM-dd";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//        LocalDate fromDateConverted = LocalDate.parse(fromDate,formatter);
//        LocalDate toDateConverted = LocalDate.parse(toDate,formatter);
//
//        var result = reservationService.findByFromDateBetween(fromDateConverted, toDateConverted);
//
//        return ResponseEntity.ok(result
//                .stream()
//                .map(Reservation::toDto)
//                .collect(Collectors.toList())
//        );
//    }

    @PostMapping("")
    public ResponseEntity<ReservationResponseDtoV1> create(@RequestBody @Validated final ReservationInputDtoV1 input) {
        var result = reservationService.create(input.toEntity());
        return ResponseEntity.ok(result.toDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDtoV1> update(@PathVariable final Long id, @RequestBody final ReservationInputDtoV1 input) {
        var result = reservationService.update(input.toEntity(id));

        return ResponseEntity.ok(result.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        reservationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
