package cz.upce.fei.postolka_BE.controller;

import cz.upce.fei.postolka_BE.domain.Room;
import cz.upce.fei.postolka_BE.dto.RoomResponseDtoV1;
import cz.upce.fei.postolka_BE.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;
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
}
