package br.com.infox.service;

import br.com.infox.displaykey.DisplayKey;
import br.com.infox.entities.Role;
import br.com.infox.entities.User;
import br.com.infox.exceptions.UserException;
import br.com.infox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Maicon
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserException(DisplayKey.get("infox.user.noneRegistredWithID", id)));
    }

    public User addRole(Long id, Role role) {
        User user = findById(id);

        user.getRoles().add(role);

        return userRepository.save(user);
    }

    public User addRoles(Long id, List<Role> roles) {
        User user = findById(id);

        roles.forEach(role -> user.getRoles().add(role));

        return userRepository.save(user);
    }
}
