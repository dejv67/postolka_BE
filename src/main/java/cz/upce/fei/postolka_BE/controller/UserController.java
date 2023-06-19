package cz.upce.fei.postolka_BE.controller;

import cz.upce.fei.postolka_BE.dto.UserInputDtoV1;
import cz.upce.fei.postolka_BE.dto.UserResponseDtoV1;
import cz.upce.fei.postolka_BE.service.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = userService.findById(id);

        return ResponseEntity.ok(result.toDto());
    }

    @GetMapping(params = "email")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) throws ResourceNotFoundException {
        var result = userService.findAllByEmailEquals(email);

        return ResponseEntity.ok(result.toDto());
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDtoV1> create(@RequestBody @Validated final UserInputDtoV1 input) {
        var result = userService.create(input.toEntity());

        return ResponseEntity.ok(result.toDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDtoV1> update(@PathVariable final Long id, @RequestBody final UserInputDtoV1 input) {
        var result = userService.update(input.toEntity(id));

        return ResponseEntity.ok(result.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
