package br.com.infox.api.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
public class AuthRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public AuthRequestDTO() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
