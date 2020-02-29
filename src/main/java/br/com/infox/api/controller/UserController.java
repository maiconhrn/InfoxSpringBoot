package br.com.infox.api.controller;

import br.com.infox.api.dto.AuthRequestDTO;
import br.com.infox.api.dto.SignupDataDTO;
import br.com.infox.api.dto.UserDTO;
import br.com.infox.exceptions.UserException;
import br.com.infox.models.User;
import br.com.infox.repository.UserRepository;
import br.com.infox.service.UserService;
import br.com.infox.util.UserUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
@RestController
@RequestMapping(path = "/api/users")
@Transactional
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<PagedResources<Resource<UserDTO>>> listAll(
            Pageable pageable, PagedResourcesAssembler<UserDTO> assembler) {
        return new ResponseEntity<>(
                assembler.toResource(
                        userRepository.findAll(pageable).map(UserUtil::toDto),
                        ControllerLinkBuilder.linkTo(UserController.class).withSelfRel()
                ), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getByID(@PathVariable("userId") @NotNull Long userId) {
        User user = userService.findById(userId);

        if (user == null) {
            throw new UserException("User with id: " + userId + " not found");
        }

        return new ResponseEntity<>(UserUtil.toDto(user), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> register(@NotNull @Valid @RequestBody SignupDataDTO signupDataDTO) {
        User user = UserUtil.fill(signupDataDTO.getUser());
        AuthRequestDTO authRequestDTO = signupDataDTO.getAuth();

        return new ResponseEntity<>(UserUtil.toDto(userService.registerNewUser(user, authRequestDTO)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable("userId") @NotNull Long userId,
                                          @NotNull @Valid @RequestBody UserDTO userDTO) {
        User userUpdated = userService.update(userId, UserUtil.fill(userDTO));
        return new ResponseEntity<>(UserUtil.toDto(userUpdated), HttpStatus.OK);
    }
}
