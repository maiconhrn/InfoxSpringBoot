package br.com.infox.util;

import br.com.infox.models.Client;
import br.com.infox.webservice.dto.ClientDTO;

/**
 * @author Maicon
 */
public class ClientUtil {

    public static ClientDTO toDto(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setCpf(client.getCpf());
        clientDTO.setAddress(AddressUtil.toDto(client.getAddress()));
        clientDTO.setBirthDate(client.getBirthDate());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setPhone(client.getPhone());

        return clientDTO;
    }

}
