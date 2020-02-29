package br.com.infox.api.controller;

import br.com.infox.AbstractInfoxApplicationTests;
import br.com.infox.api.dto.AuthRequestDTO;
import br.com.infox.api.dto.SignupDataDTO;
import br.com.infox.api.dto.UserDTO;
import br.com.infox.models.builders.UserBuilder;
import br.com.infox.util.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Maicon
 */
public class UserControllerTest extends AbstractInfoxApplicationTests {
    @Override
    @Before
    public void setUp() throws ParseException {
        super.setUp();
    }

    private MvcResult createUser() throws Exception {
        UserDTO userDTO = UserUtil.toDto(UserBuilder.anUser()
                .withBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("16/06/1998"))
                .withCpf("10495058912")
                .withFirstName("Maicon Henrique")
                .withLastName("Rodrigues do Nascimento")
                .withEmail("maiconhrn@hotmail.com")
                .withUsername("maiconhrn")
                .build());

        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("maiconhrn");
        authRequestDTO.setPassword("Maicon123");

        SignupDataDTO signupDataDTO = new SignupDataDTO();
        signupDataDTO.setUser(userDTO);
        signupDataDTO.setAuth(authRequestDTO);

        return mvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(signupDataDTO))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    }

    @Test
    public void resgisterUserWithValidFields() throws Exception {
        MvcResult mvcResult = createUser();

        MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        UserDTO userReponse = mapFromJson(response.getContentAsString(), UserDTO.class);
        assertThat(userReponse.getId()).isNotNull();
        assertThat(userReponse.getId()).isGreaterThan(0L);
    }
}