package cz.upce.fei.postolka_BE.repository;

import cz.upce.fei.postolka_BE.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r JOIN r.rooms room WHERE room.id = :roomId " +
            "and ((:fDate >= r.fromDate and :fDate <= r.toDate) or (:tDate >= r.fromDate and :tDate <= r.toDate)" +
            "or (r.fromDate >= :fDate and r.fromDate <= :tDate) or (r.toDate >= :fDate and r.toDate <= :tDate))")
    List<Reservation> findByDatesAndRoomId(@Param("fDate") LocalDate fDate, @Param("tDate") LocalDate tDate, @Param("roomId")Long roomId);

    List<Reservation> findAllByAuthor_Id(@Param("authorId")Long authorId);
}