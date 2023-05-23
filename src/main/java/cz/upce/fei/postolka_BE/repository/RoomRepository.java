package cz.upce.fei.postolka_BE.repository;

import cz.upce.fei.postolka_BE.domain.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
}
