package org.java4me.alexandrina.service;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.database.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;


}
