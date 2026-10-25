package demospringboot1.demospringboot1.repository;

import demospringboot1.demospringboot1.Dtos.UserRequestDto;
import demospringboot1.demospringboot1.Dtos.UserResponseDto;
import demospringboot1.demospringboot1.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final Map<Long, User> entityManager;

    private final AtomicLong idCounter = new AtomicLong(0);

    public UserRepository() {
        this.entityManager = new HashMap<>();
    }

    public UserResponseDto save(UserRequestDto requestDto) {
        User user = dtoToEntity(requestDto);
//        return entityToDto(entityManager.put(user.getId(), user));
//        return entityToDto(Objects.requireNonNull(entityManager.put(user.getId(), user)));
        entityManager.put(user.getId(), user);

        return entityToDto(user);
    }


    public UserResponseDto getById(Long id) {
        User user = entityManager.get(id);
        return entityToDto(user);
    }

    public List<UserResponseDto> getAll() {
        Collection<User> userList = entityManager.values();
        return userList.stream().map(this::entityToDto).toList();
    }

    public void deleteById(Long id) {
        entityManager.remove(id);
    }

    public void deleteAll() {
        entityManager.clear();
    }




//    Helper methods
    private UserResponseDto entityToDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getDescription());
    }

    private User dtoToEntity(UserRequestDto requestDto) {
        return User.builder()
                   .id(idCounter.incrementAndGet())
                   .name(requestDto.name())
                   .description(requestDto.description())
                   .build();
    }

}
