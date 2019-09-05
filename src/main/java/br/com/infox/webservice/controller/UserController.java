package br.com.infox.webservice.controller;

import br.com.infox.displaykey.DisplayKey;
import br.com.infox.exceptions.UserException;
import br.com.infox.models.User;
import br.com.infox.repository.UserRepository;
import br.com.infox.util.UserUtil;
import br.com.infox.webservice.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
@RestController
@RequestMapping(value = "/users")
@Transactional
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private List<UserDTO> toDto(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(usuario -> usersDTO.add(UserUtil.toDto(usuario)));

        return usersDTO;
    }

    private UserDTO save(User user) {
        if (user != null) {
            try {
                user = userRepository.save(user);
            } catch (Exception e) {
                throw new UserException(DisplayKey.get("infox.user.add.error") + e.getMessage());
            }
            return UserUtil.toDto(user);
        }

        return new UserDTO();
    }

    @GetMapping("/")
    public List<UserDTO> listUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserException(DisplayKey.get("infox.user.noneRegistred"));
        }

        return toDto(users);
    }

    @GetMapping("/{id}")
    public UserDTO getUsuarioByID(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElse(new User());

        if (user.getId() == null) {
            throw new UserException(DisplayKey.get("infox.user.noneRegistredWithID", id));
        }

        return UserUtil.toDto(user);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        if (userDTO == null) {
            throw new UserException("Usuário nulo");
        }

        User user = UserUtil.fill(userDTO);
        user.setHashPassword(userDTO.getPassword());
        return save(user);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        if (id == null || id < 1) {
            throw new UserException(DisplayKey.get("infox.user.update.id.error", id));
        }

        if (userDTO == null) {
            throw new UserException("Usuário nulo");
        }

        User user = UserUtil.fill(userDTO);
        return save(user);
    }
}
