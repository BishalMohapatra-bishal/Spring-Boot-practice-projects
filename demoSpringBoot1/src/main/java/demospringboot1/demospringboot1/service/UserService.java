package demospringboot1.demospringboot1.service;

import demospringboot1.demospringboot1.Dtos.UserRequestDto;
import demospringboot1.demospringboot1.Dtos.UserResponseDto;
import demospringboot1.demospringboot1.model.User;
import demospringboot1.demospringboot1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponseDto createUser(UserRequestDto requestDto) {

        if (requestDto == null) throw new NullPointerException("The data cannot be null");

        return repository.save(requestDto);
    }

    public UserResponseDto getUserById(Long id) {
        return repository.getById(id);
    }

    public List<UserResponseDto> getAllUser() {
        return repository.getAll();
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllUser() {
        repository.deleteAll();
    }

}
