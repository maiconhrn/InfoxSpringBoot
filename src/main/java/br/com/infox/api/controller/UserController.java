package br.com.infox.api.controller;

import br.com.infox.api.dto.AuthRequestDTO;
import br.com.infox.api.dto.SignupDataDTO;
import br.com.infox.api.dto.UserDTO;
import br.com.infox.displaykey.DisplayKey;
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

import static br.com.infox.util.BeanUtil.copyNonNullProperties;

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
            throw new UserException(DisplayKey.get("infox.user.noneRegistredWithID", userId));
        }

        return new ResponseEntity<>(UserUtil.toDto(user), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> register(@NotNull @Valid @RequestBody SignupDataDTO signupDataDTO) {
        User user = UserUtil.fill(signupDataDTO.getUser());
        AuthRequestDTO authRequestDTO = signupDataDTO.getAuth();

        return new ResponseEntity<>(UserUtil.toDto(userService.registerNewUser(user, authRequestDTO)), HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable("userId") @NotNull Long userId,
                                          @NotNull @Valid @RequestBody UserDTO userDTO) {
        User userToUpdate = userService.findById(userId);

        if (userToUpdate == null) {
            throw new UserException("Client with id: " + userId + " not found");
        }

        User userWithNewValues = UserUtil.fill(userDTO);
        copyNonNullProperties(userWithNewValues, userToUpdate);

        return new ResponseEntity<>(save(userToUpdate), HttpStatus.OK);
    }
}
