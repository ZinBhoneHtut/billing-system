package com.zbh.billingsystem.config;

import com.zbh.billingsystem.entity.Role;
import com.zbh.billingsystem.entity.User;
import com.zbh.billingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author ZinBhoneHtut
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    private final PasswordEncoder encoder;
    private final UserService userService;

    @Value("${user.default.password}")
    private String defaultPassword;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("======= Starting Initialize ========");
        initUser();
        log.info("======= Finished Initialize ========");
    }

    private void initUser() {
        if (userService.count() == 0) {
            defaultPassword = encoder.encode(defaultPassword);
            User adminUser = User.builder().name("admin").email("admin@gmail.com")
                    .phoneNumber("959123456789").password(defaultPassword)
                    .role(Role.ADMIN).build();
            User normalUser = User.builder().name("user").email("user@gmail.com")
                    .phoneNumber("959987654321").password(defaultPassword)
                    .role(Role.USER).build();
            userService.saveAll(Arrays.asList(adminUser, normalUser));
            log.info("User has been initialized");
        }
    }

}
