package cz.upce.fei.postolka_BE.service;

import cz.upce.fei.postolka_BE.domain.Reservation;
import cz.upce.fei.postolka_BE.domain.Room;
import cz.upce.fei.postolka_BE.dto.ReservationResponseDtoV1;
import cz.upce.fei.postolka_BE.dto.RoomResponseDtoV1;
import cz.upce.fei.postolka_BE.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<Reservation> findByDatesAndRoomId(LocalDate fromDate, LocalDate toDate, Long id) {
        var result = reservationRepository.findByDatesAndRoomId(fromDate, toDate, id);

        return result;
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDtoV1> findByUserId(Long id) throws ResourceNotFoundException {
        var result = reservationRepository.findAllByAuthor_Id(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.stream()
                .map(Reservation::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Reservation> findAll() throws ResourceNotFoundException {
        var iterable = reservationRepository.findAll();

        List<Reservation> result = new ArrayList<Reservation>();
        iterable.forEach(result::add);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @Transactional
    public Reservation create(final Reservation reservation){
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation update(final Reservation reservation){
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void delete(final Long id) {
        reservationRepository.deleteById(id);
    }
}
