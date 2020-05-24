package br.com.infox.controller;

import br.com.infox.model.dto.AuthRequestDTO;
import br.com.infox.model.dto.AuthResponseDTO;
import br.com.infox.service.UserService;
import br.com.infox.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
@Api(value = "API for authentication")
@RestController
@CrossOrigin
@RequestMapping(path = {"/api"})
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @ApiOperation(value = "Authenticate an User", response = AuthResponseDTO.class)
    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(@NotNull @Valid @RequestBody AuthRequestDTO authRequestDTO) {
        authenticate(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        UserDetails userDetails = userService.loadUserByUsername(authRequestDTO.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.CREATED);
    }
}
