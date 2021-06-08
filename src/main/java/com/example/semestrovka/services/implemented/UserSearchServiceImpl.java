package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.SearchForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSearchServiceImpl implements UserSearchService {
    private final UsersRepository usersRepository;

    @Override
    public Page<UserDto> findAllByRequestBody(SearchForm searchForm) {
        PageRequest pageRequest = PageRequest.of(searchForm.getPage() - 1, searchForm.getSize());
        Page<User> userList = usersRepository.findAllByUsernameIgnoreCase(searchForm.getName(), pageRequest);
        return userList.map(UserDto::fromUser);
        // TODO: сделать постраничную пагинацию и прикрутить сюда маппер, пока что отваливается
    }
}
