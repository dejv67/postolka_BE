package cz.upce.fei.postolka_BE.controller;

import cz.upce.fei.postolka_BE.dto.ReservationInputDtoV1;
import cz.upce.fei.postolka_BE.dto.ReservationResponseDtoV1;
import cz.upce.fei.postolka_BE.service.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping(path ="", params = {"userId"})
    public ResponseEntity<?> findByUserId(@RequestParam final Long userId) throws ResourceNotFoundException {
        var result = reservationService.findByUserId(userId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<ReservationResponseDtoV1> create(@RequestBody @Validated final ReservationInputDtoV1 input) {
        var result = reservationService.create(input.toEntity());
        return ResponseEntity.ok(result.toDto());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        reservationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
