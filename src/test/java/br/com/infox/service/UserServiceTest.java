package br.com.infox.service;

import br.com.infox.entities.Role;
import br.com.infox.entities.User;
import br.com.infox.manipulators.builders.RoleBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author Maicon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void addRole() {
        Role role = RoleBuilder.aRole()
                .withName("SU")
                .withDescription("System Super User Permissions")
                .build();

        User user = userService.addRole(1L, role);

        assertTrue(user.getRoles().stream().anyMatch(r -> r.getName().equals("SU")));
    }
}