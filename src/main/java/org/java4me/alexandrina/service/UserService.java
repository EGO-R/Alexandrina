package org.java4me.alexandrina.service;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.database.repository.UserRepository;
import org.java4me.alexandrina.dto.UserCreateEditDto;
import org.java4me.alexandrina.dto.UserReadDto;
import org.java4me.alexandrina.mapper.UserCreateEditDtoMapper;
import org.java4me.alexandrina.mapper.UserReadDtoMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserReadDtoMapper userReadDtoMapper;
    private final UserCreateEditDtoMapper userCreateEditDtoMapper;

    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(userCreateEditDtoMapper::map)
                .map(userRepository::saveAndFlush)
                .map(userReadDtoMapper::map)
                .orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
