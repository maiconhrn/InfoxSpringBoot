package br.com.infox.api.controller;

import br.com.infox.AbstractInfoxApplicationTests;
import br.com.infox.api.dto.AuthRequestDTO;
import br.com.infox.models.builders.UserBuilder;
import br.com.infox.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Maicon
 */
public class JwtAuthenticationControllerTest extends AbstractInfoxApplicationTests {
    @MockBean
    protected UserRepository userRepository;

    @Override
    @Before
    public void setUp() throws ParseException {
        super.setUp();

        Mockito.when(userRepository.findByUsername("maiconhrn"))
                .thenReturn(Optional.of(UserBuilder.anUser()
                        .withId(1L)
                        .withBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("16/06/1998"))
                        .withCpf("10495058912")
                        .withFirstName("Maicon Henrique")
                        .withLastName("Rodrigues do Nascimento")
                        .withEmail("maiconhrn@hotmail.com")
                        .withUsername("maiconhrn")
                        .withHashPassword("$2a$10$xABUGpGh2uwFOKFDIUyky.cuvero09hCj/2AhZfjEK13l8yfJzA7.")
                        .build()));
    }

    @Test
    public void authenticateWithIncorrectPasswordExpectUnauthorizedHTTPStatus() {
        try {
            AuthRequestDTO authRequestDTO = new AuthRequestDTO();
            authRequestDTO.setUsername("maiconhrn");
            authRequestDTO.setPassword("Maicon123");

            mvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(authRequestDTO))
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
        } catch (Exception e) {
            assertThat(e).hasMessageContaining("INVALID_CREDENTIALS");
        }
    }

    @Test
    public void authenticateWithCorrectPasswordExpectCreatedHTTPStatus() throws Exception {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("maiconhrn");
        authRequestDTO.setPassword("Maicon321");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(authRequestDTO))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}