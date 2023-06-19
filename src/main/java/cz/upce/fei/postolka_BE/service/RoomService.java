package cz.upce.fei.postolka_BE.service;

import cz.upce.fei.postolka_BE.domain.Room;
import cz.upce.fei.postolka_BE.service.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public Room findById(Long id) throws ResourceNotFoundException {
        var result = roomRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
    }

    @Transactional(readOnly = true)
    public List<Room> findAll() throws ResourceNotFoundException {
        var iterable = roomRepository.findAll();

        List<Room> result = new ArrayList<Room>();
        iterable.forEach(result::add);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result;
    }
}
