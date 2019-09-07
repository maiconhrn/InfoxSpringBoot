package br.com.infox.webservice.controller;

import br.com.infox.displaykey.DisplayKey;
import br.com.infox.exceptions.UserException;
import br.com.infox.models.User;
import br.com.infox.repository.UserRepository;
import br.com.infox.service.UserService;
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

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
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
                user = userService.saveUser(user);
                return UserUtil.toDto(user);
            } catch (Exception e) {
                throw new UserException(DisplayKey.get("infox.user.add.error") + e.getMessage());
            }
        }

        return new UserDTO();
    }

    @GetMapping("/")
    public List<UserDTO> listAll() {
        List<User> users = userRepository.findAll();

        return (users != null && !users.isEmpty()) ? toDto(users) : new ArrayList<>();
    }

    @GetMapping("/{id}")
    public UserDTO getByID(@PathVariable("id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            throw new UserException(DisplayKey.get("infox.user.noneRegistredWithID", id));
        }

        return UserUtil.toDto(user);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO register(@RequestBody UserDTO userDTO) {
        if (userDTO == null) {
            throw new UserException("Usuário nulo");
        }

        User user = UserUtil.fill(userDTO);
        return save(user);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        if (id == null || id < 1) {
            throw new UserException(DisplayKey.get("infox.user.update.id.error", id));
        }

        if (userDTO == null) {
            throw new UserException("Somente campos nulos para atualizar");
        }

        User user = userService.findById(id);
        UserUtil.merge(userDTO, user);

        return save(user);
    }
}
