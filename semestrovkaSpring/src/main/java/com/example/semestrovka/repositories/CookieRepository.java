package com.example.semestrovka.repositories;

import com.example.semestrovka.models.Cookie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookieRepository extends JpaRepository<Cookie, Long> {
}
