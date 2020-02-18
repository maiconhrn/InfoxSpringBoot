package br.com.infox.api.controller;

import br.com.infox.api.dto.AuthRequestDTO;
import br.com.infox.api.dto.AuthResponseDTO;
import br.com.infox.service.UserService;
import br.com.infox.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
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

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(@NotNull @Valid @RequestBody AuthRequestDTO authRequestDTO) throws Exception {
        authenticate(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        UserDetails userDetails = userService.loadUserByUsername(authRequestDTO.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }
}
