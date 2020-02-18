package br.com.infox.api.dto;

/**
 * @author Maicon
 */
public class AuthResponseDTO {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
