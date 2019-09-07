package br.com.infox.service;

import br.com.infox.displaykey.DisplayKey;
import br.com.infox.exceptions.UserException;
import br.com.infox.models.Role;
import br.com.infox.models.User;
import br.com.infox.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Maicon
 */
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserException(DisplayKey.get("infox.user.noneRegistredWithID", id)));
    }

    public User saveUser(User user) {
        if (user == null) {
            throw new UserException("Could not save a null User Object");
        }

        return userRepository.save(user);
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

    public User login(String username, String password) {
        return userRepository.login(username, password).orElse(null);
    }
}
