package cz.upce.fei.postolka_BE.repository;

import cz.upce.fei.postolka_BE.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>  {
    User findByEmailEquals(String email);
}
