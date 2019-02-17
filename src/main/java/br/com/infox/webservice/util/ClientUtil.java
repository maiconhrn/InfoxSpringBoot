package br.com.infox.webservice.util;

import br.com.infox.entities.Client;
import br.com.infox.webservice.model.dto.ClientDTO;

/**
 * @author Maicon
 */
public class ClientUtil {

    public static ClientDTO toDto(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setCpf(client.getCpf());
        clientDTO.setAddress(AddressUtil.toDto(client.getAddress()));
        clientDTO.setBirtyDate(client.getBirtyDate());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setPhone(client.getPhone());

        return clientDTO;
    }

}
