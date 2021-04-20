package com.example.semestrovka.repositories;

import com.example.semestrovka.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByActivationCode(String code);

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query("select u from User u where lower(u.username) like lower(concat('%', :nameToFind,'%')) ")
    Page<User> findAllByUsernameIgnoreCase(@Param("nameToFind") String username,
                                           Pageable pageable);

}
