package cz.upce.fei.postolka_BE.repository;

import cz.upce.fei.postolka_BE.domain.Reservation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
    List<Reservation> findByFromDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
