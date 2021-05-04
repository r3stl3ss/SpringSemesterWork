package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDto> getUsers(int number) {
        Pageable pageable = PageRequest.of(number, 5);
        Page<User> page = usersRepository.findAll(pageable);

        return page.map(UserDto::fromUser);
    }

    @SneakyThrows
    @Override
    public UserDto getUser(Long id) {
        return UserDto.fromUser(usersRepository.findById(id)
                .orElseThrow((Supplier<Throwable>) () ->
                        new UsernameNotFoundException("User with this nickname does not exist")));
    }

    @Override
    public UserDto createUser(SignUpForm form) {
        User newUser = User.builder()
                .name(form.getName())
                .email(form.getEmail())
                .username(form.getUsername())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();
        usersRepository.save(newUser);
        return UserDto.fromUser(newUser);
    }

    @Override
    public UserDto updateUser(Long id, SignUpForm form) {
        User updateableUser = usersRepository.findById(id).orElseThrow(IllegalStateException::new);

        updateableUser.setUsername(form.getUsername());
        updateableUser.setEmail(form.getEmail());

        usersRepository.save(updateableUser);
        return UserDto.fromUser(updateableUser);
    }

    @Override
    public void deleteUser(Long id) {
        User deleteable = usersRepository.findById(id).orElseThrow(IllegalStateException::new);

        usersRepository.delete(deleteable);
    }
}
