package br.com.infox.api.dto;

/**
 * @author Maicon
 */
public class SignupDataDTO {

    private UserDTO user;
    private AuthRequestDTO auth;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AuthRequestDTO getAuth() {
        return auth;
    }

    public void setAuth(AuthRequestDTO auth) {
        this.auth = auth;
    }
}
