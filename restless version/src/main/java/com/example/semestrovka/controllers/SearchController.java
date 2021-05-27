package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.SearchForm;
import com.example.semestrovka.security.details.UserDetailsImpl;
import com.example.semestrovka.services.interfaces.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final UserSearchService userSearchService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<UserDto> getUsersBySearchForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody SearchForm searchForm) {
        return userSearchService.findAllByRequestBody(searchForm);
    }

    @GetMapping
    public String getSearchPage() {
        return "searchPage";
    }
}