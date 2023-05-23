package cz.upce.fei.postolka_BE.service;

import cz.upce.fei.postolka_BE.domain.Reservation;
import cz.upce.fei.postolka_BE.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<Reservation> findByFromDateBetween(LocalDateTime fromDate, LocalDateTime toDate) throws ResourceNotFoundException {
        var result = reservationRepository.findByFromDateBetween(fromDate, toDate);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @Transactional(readOnly = true)
    public Reservation findById(Long id) throws ResourceNotFoundException {
        var result = reservationRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
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
