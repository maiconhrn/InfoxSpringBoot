package br.com.infox.service;

import br.com.infox.api.dto.AuthRequestDTO;
import br.com.infox.exceptions.UserException;
import br.com.infox.models.Role;
import br.com.infox.models.User;
import br.com.infox.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static br.com.infox.util.BeanUtil.copyNonNullProperties;

/**
 * @author Maicon
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User findById(@NotNull Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserException("User with id: " + id + " not found"));
    }

    public User saveUser(@NotNull User user) {
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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("User with username: " + " not found"));
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User registerNewUser(User user, AuthRequestDTO authRequestDTO) {
        if (userExists(authRequestDTO.getUsername())) {
            throw new UserException("There is an account with that username:"
                    + authRequestDTO.getUsername());
        }

        user.setHashPassword(passwordEncoder.encode(authRequestDTO.getPassword()));

        return userRepository.save(user);
    }

    public User update(User userToUpdate, User userWithNewValues) {
        if (userToUpdate == null || userWithNewValues == null) {
            throw new UserException("User object cant be null");
        }

        copyNonNullProperties(userWithNewValues, userToUpdate);

        return userRepository.save(userToUpdate);
    }

    public User update(Long userId, User userWithNewValues) {
        User userToUpdate = findById(userId);

        if (userToUpdate == null) {
            throw new UserException("User with id: " + userId + " not found");
        }

        return update(userToUpdate, userWithNewValues);
    }

    //    private void checkGrantAuthorities(User user, List<GrantedAuthority> listGrantAuthority) {
//        if (user != null && user.getRoles() != null && user.getRoles().isEmpty() == false)
//            for (Role roleUser : user.getRoles()) {
//                final String PREFIX = "ROLE_";
//                String role = PREFIX + roleUser.getDescription();
//                listGrantAuthority.add(new GrantedAuthorityImpl(role));
//            }
//    }

    private UserDetails validateUser(String username, List<GrantedAuthority> listGrantAuthority, User user) {
        UserDetails userDetails = null;
        if (user != null) {
            userDetails = new org.springframework.security.core.userdetails
                    .User(username, user.getHashPassword(), true, true, true, true, listGrantAuthority);
        }
        return userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
        User user = findByUsername(username);
//        checkGrantAuthorities(user, listGrantAuthority);
        return validateUser(username, listGrantAuthority, user);
    }
}
