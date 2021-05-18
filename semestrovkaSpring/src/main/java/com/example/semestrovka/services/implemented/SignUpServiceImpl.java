package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.exceptions.ActivationCodeDoesNotExistException;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.SendMailService;
import com.example.semestrovka.services.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public boolean signUp(SignUpForm form) {
        User newUser = User.builder()
                .name(form.getName())
                .email(form.getEmail())
                .username(form.getUsername())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();

        Optional<User> userFromBase = usersRepository.findByUsername(form.getUsername());

        if (userFromBase.isPresent()) {
            return false;
        }

        newUser.setActivationCode(UUID.randomUUID().toString());
        String message = String.format(
                "Hello, %s! \n" +
                "Это ваша ссылка для активации аккаунта: http://localhost/activate/%s ",
                newUser.getUsername(),
                newUser.getActivationCode());
        //TODO: прикрутить отдельный поток к отправке сообщений
        sendMailService.send(newUser.getEmail(), "Account activation", message);
        usersRepository.save(newUser);
        return true;
    }

    @Override
    public boolean activateUser(String code) throws ActivationCodeDoesNotExistException {
        User user = usersRepository.findByActivationCode(code).orElseThrow(() -> new ActivationCodeDoesNotExistException("Активационный код не работает"));
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        usersRepository.save(user);
        return true;
    }
}

