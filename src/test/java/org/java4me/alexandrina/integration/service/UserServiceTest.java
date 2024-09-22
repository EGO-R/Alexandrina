package org.java4me.alexandrina.integration.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.java4me.alexandrina.database.entity.Role;
import org.java4me.alexandrina.database.repository.UserRepository;
import org.java4me.alexandrina.integration.IntegrationTestBase;
import org.java4me.alexandrina.service.UserService;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class UserServiceTest extends IntegrationTestBase {
    private final UserService userService;
    private final UserRepository userRepository;

    @Test
    void testPersistence() {
        userService.testPersistence(1L);

        var user = userRepository.findById(1L).orElseThrow();

        Assertions.assertThat(user.getRole()).isSameAs(Role.USER);
    }
}
