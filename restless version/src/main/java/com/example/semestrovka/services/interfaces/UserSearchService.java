package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.SearchForm;
import org.springframework.data.domain.Page;

public interface UserSearchService {
    Page<UserDto> findAllByRequestBody(SearchForm searchForm);
}
