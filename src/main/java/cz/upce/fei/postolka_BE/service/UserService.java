package cz.upce.fei.postolka_BE.service;

import cz.upce.fei.postolka_BE.domain.User;
import cz.upce.fei.postolka_BE.service.exception.ResourceNotFoundException;
import cz.upce.fei.postolka_BE.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findAllByEmailEquals(String email) throws ResourceNotFoundException {
        var result = userRepository.findByEmailEquals(email);

        if (result == null) {
            throw new ResourceNotFoundException();
        }
        return result;
    }


    @Transactional(readOnly = true)
    public User findById(Long id) throws ResourceNotFoundException {
        var result = userRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
    }

    @Transactional
    public User create(final User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(final User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailEquals(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return user;
    }
}
