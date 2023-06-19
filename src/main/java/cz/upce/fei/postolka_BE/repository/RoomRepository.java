package cz.upce.fei.postolka_BE.repository;

import cz.upce.fei.postolka_BE.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
