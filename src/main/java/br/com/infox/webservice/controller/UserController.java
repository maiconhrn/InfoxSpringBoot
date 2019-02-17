package br.com.infox.webservice.controller;

import br.com.infox.displaykey.DisplayKey;
import br.com.infox.entities.User;
import br.com.infox.exceptions.UserException;
import br.com.infox.repository.UserRepository;
import br.com.infox.webservice.model.dto.UserDTO;
import br.com.infox.webservice.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
@RestController
@RequestMapping(value = "/api/user")
@Transactional
public class UserController {

    @Autowired
    UserRepository userRepository;

    private List<UserDTO> toDto(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(usuario -> usersDTO.add(UserUtil.toDto(usuario)));

        return usersDTO;
    }

    private UserDTO save(UserDTO user) {
        User u = new User();
        if (user != null) {
            u = UserUtil.fill(user);
            try {
                u = userRepository.save(u);
            } catch (Exception e) {
                throw new UserException(DisplayKey.get("infox.user.add.error") + e.getMessage());
            }
        }
        return UserUtil.toDto(u);
    }

    @GetMapping("/list")
    public List<UserDTO> listUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserException(DisplayKey.get("infox.user.noneRegistred"));
        }

        return toDto(users);
    }

    @GetMapping("/listByFirstName")
    public List<UserDTO> getUsuarioWhereNameLike(@RequestParam("firstName") String firstName) {
        List<User> users = userRepository.findAllByNomeIsLike(firstName);

        if (users.isEmpty()) {
            throw new UserException(DisplayKey.get("infox.user.noneRegistredWithName", firstName));
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

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerUser(@RequestBody UserDTO user) {
        return save(user);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUser(@RequestBody UserDTO user) {
        if (user.getId() == null || user.getId() < 1) {
            throw new UserException(DisplayKey.get("infox.user.update.id.error", user.getId()));
        }

        return save(user);
    }
}
